package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Credit;
import dev.roysez.financemanager.model.User;
import dev.roysez.financemanager.service.TransactionService;
import dev.roysez.financemanager.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {


    @Autowired
    TransactionService transactionService ;

    @RequestMapping(value = {"","/"})
    public String homePage(Model model)
    {
        try {
            model.addAttribute("listOfTransactions",transactionService.findAll());
        } catch (IOException e) {
            model.addAttribute("Error","ЩОСЬ ПІШЛО не таК ");
        }
        return "index";
    }

    @RequestMapping(value = "/credits")
    public String creditPage(ModelMap modelMap){

        return "credit";
    }

    @RequestMapping(value = "/deposits")
    public String depositPage(ModelMap modelMap){

        return "deposit";
    }


}
