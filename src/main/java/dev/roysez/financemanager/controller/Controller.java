package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Credit;
import dev.roysez.financemanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {



    @RequestMapping(value = {"","/"})
    public String homePage(Model model)
    {

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
