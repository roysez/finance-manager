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

import java.io.IOException;
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
                return "index";
            }
            Set<Transaction> transactionSet = transactionService.findAll();
            User user = userService.getUser();

            model.addAttribute("listOfTransactions", transactionSet);
            model.addAttribute("user",user);

            Category category = new Category();



            model.addAttribute("category",category);

        } catch (Exception e) {
            model.addAttribute("error", "Something went wrong");
        }
        return "index";
    }

    @RequestMapping(value = "/transactions",method = RequestMethod.POST)
    public String createTransaction(){
        Transaction transaction = new Transaction();
        return "index";
    }
}
