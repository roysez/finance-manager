package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Credit;
import dev.roysez.financemanager.model.Deposit;
import dev.roysez.financemanager.service.CategoryService;
import dev.roysez.financemanager.service.CreditService;
import dev.roysez.financemanager.service.TransactionService;
import dev.roysez.financemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
@RequestMapping(value = "/credits")
public class CreditController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    CreditService creditService;


    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String creditPage(Model model, @ModelAttribute("error") String error){

        if(!error.isEmpty()){
            model.addAttribute("error",error);
        }

        try {

            Set<Credit> credits = creditService.findAll();
            model.addAttribute("credit",new Credit());
            model.addAttribute("credits",credits);
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
        }
        return "credit";
    }
}
