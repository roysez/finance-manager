package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Credit;
import dev.roysez.financemanager.model.User;
import dev.roysez.financemanager.repository.CreditRepository;
import dev.roysez.financemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CreditRepository creditRepository;

    @RequestMapping(value = "/")
    public String homePage(ModelMap modelMap)
    {
        User user = userRepository.findByUsername("roysez");
        modelMap.put("list",user.getTransactions());
        modelMap.put("user",user);
        return "index";
    }

    @RequestMapping(value = "/credits")
    public String creditPage(ModelMap modelMap){
        User user = userRepository.findByUsername("roysez");
        modelMap.put("creditList",user.getCredits());
        modelMap.put("user",user);
        return "credit";
    }

    @RequestMapping(value = "/deposits")
    public String depositPage(ModelMap modelMap){
        User user = userRepository.findByUsername("roysez");
        modelMap.put("depositList",user.getDeposits());
        modelMap.put("user",user);
        return "deposit";
    }


}
