package dev.roysez.financemanager.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import dev.roysez.financemanager.model.Category;
import dev.roysez.financemanager.model.Transaction;
import dev.roysez.financemanager.model.User;
import dev.roysez.financemanager.service.CategoryService;
import dev.roysez.financemanager.service.TransactionService;
import dev.roysez.financemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

@Controller
public class TransactionController {


    @Autowired
    TransactionService transactionService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"", "/", "/transactions"})
    public String transactionPage(Model model, @ModelAttribute("error") String error) {
        try {

            if (!error.isEmpty()) {
                model.addAttribute("error", error);
            }

            Set<Transaction> transactionSet = transactionService.findAll();
            User user = userService.getUser();

            model.addAttribute("listOfTransactions", transactionSet);
            model.addAttribute("user", user);

            Category category = new Category();
            model.addAttribute("category", category);
            Transaction transaction = new Transaction();
            model.addAttribute("transaction", transaction);
            Set<Category> categories = categoryService.findAll();
            model.addAttribute("categoriesList", categories);


        } catch (JsonMappingException e ){
            model.addAttribute("error","Check your file, something went wrong");
        }
        catch (Exception e) {
            model.addAttribute("error", "Something went wrong");
        }
        return "index";
    }


    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTransaction(@PathVariable("id") String id) {


        transactionService.delete(Integer.valueOf(id));

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/transactions/expenses", method = RequestMethod.POST)
    public String postExpense(Transaction transaction,
                              @RequestParam("selectedCategory") String sC,
                              RedirectAttributes redir) {

        transaction.setTrType(Transaction.TransactionType.TRANSACTION_EXPENSE);
        transaction.setDate(new Date());

        if (sC.contains("["))
            sC = sC.split("\\[")[0].trim();

        try {
            Category category = categoryService.findOneByName(sC);
            transaction.setCategory(category);


            User user = userService.getUser();

            Long userBalance = user.getBalance();

            Long tax = category.getTax() * transaction.getSum() / 100;

            transaction.setSum(transaction.getSum() + tax);
            if (userBalance - transaction.getSum() - tax < 0)
                throw new IllegalStateException("You don't have enough money to record this expense");

            user.setBalance(userBalance - transaction.getSum() - tax);

            System.out.println("Transaction created [" + transaction.getSum() + "] - with tax {" + tax + "}");
            transactionService.save(transaction);
            userService.saveUser(user);

        } catch (IOException e) {
            redir.addFlashAttribute("error", "Error while reading user information");
        } catch (IllegalStateException e) {
            redir.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/transactions";
    }

    @RequestMapping(value = "/transactions/income", method = RequestMethod.POST)
    public String postIncome(Transaction transaction,
                             @RequestParam("selectedCategory") String sC,
                             RedirectAttributes redir) {

        transaction.setTrType(Transaction.TransactionType.TRANSACTION_INCOME);
        transaction.setDate(new Date());

        try {


            Category category = categoryService.findOneByName(sC);
            transaction.setCategory(category);

        } catch (IOException e) {
            e.printStackTrace();
            redir.addFlashAttribute("error", "Category can't be found");
        }


        try {

            User user = userService.getUser();

            Long userBalance = user.getBalance();


            user.setBalance(userBalance + transaction.getSum());

            transactionService.save(transaction);
            userService.saveUser(user);

        } catch (IOException e) {
            redir.addFlashAttribute("error", "Error while reading user information");
        }
        return "redirect:/transactions";
    }
}
