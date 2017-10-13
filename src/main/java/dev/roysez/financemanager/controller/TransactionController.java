package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Category;
import dev.roysez.financemanager.model.Transaction;
import dev.roysez.financemanager.model.User;
import dev.roysez.financemanager.service.CategoryService;
import dev.roysez.financemanager.service.TransactionService;
import dev.roysez.financemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;

@org.springframework.stereotype.Controller
public class TransactionController {


    @Autowired
    TransactionService transactionService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"", "/","/transactions"})
    public String transactionPage(Model model, @ModelAttribute("error") String error) {
        try {

            if(!error.isEmpty()){
                model.addAttribute("error",error);
            }

            Set<Transaction> transactionSet = transactionService.findAll();
            User user = userService.getUser();

            model.addAttribute("listOfTransactions", transactionSet);
            model.addAttribute("user",user);

            Category category = new Category();
            model.addAttribute("category",category);
            Transaction transaction = new Transaction();
            model.addAttribute("transaction",transaction);
            Set<Category> categories = categoryService.findAll();
            model.addAttribute("categoriesList",categories);


        } catch (Exception e) {
            model.addAttribute("error", "Something went wrong");
        }
        return "index";
    }

    @RequestMapping(value = "/transactions/expenses",method = RequestMethod.POST)
    public String postExpense(Transaction transaction,
                                    @RequestParam("selectedCategory") String sC,
                                    RedirectAttributes redir){

        transaction.setTrType(Transaction.TransactionType.TRANSACTION_EXPENSE);
        transaction.setDate(new Date());

        try {


            Category category = categoryService.findAll()
                                                .stream()
                                                .filter(element -> element.getCategoryName()
                                                                            .equals(sC))
                                                .reduce((category1, category2) -> {
                                                    throw new IllegalStateException();
                                                })
                                                .orElseThrow(NoSuchElementException::new);

            transaction.setCategory(category);
        } catch (IOException e) {
            e.printStackTrace();
            redir.addFlashAttribute("error","Category can't be found");
        }



        try {

        User user = userService.getUser();

        Long userBalance = user.getBalance();

        if(userBalance -transaction.getSum()<0)
            throw new IllegalStateException("You don't have enough money to record this expense");

        user.setBalance(userBalance-transaction.getSum());

        transactionService.save(transaction);
        userService.saveUser(user);

        } catch (IOException e) {
            redir.addFlashAttribute("error","Error while reading user information");
        } catch (IllegalStateException e) {
            redir.addFlashAttribute("error",e.getMessage());
        }
        return "redirect:/transactions";
    }
}
