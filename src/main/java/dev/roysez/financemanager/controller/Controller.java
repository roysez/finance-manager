package dev.roysez.financemanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {


    @RequestMapping(value = "/")
    public String homePage(){
        return "index";
    }

    @RequestMapping(value = "/credits")
    public String creditPage(){
        return "credit";
    }

    @RequestMapping(value = "/deposits")
    public String depositPage(){
        return "deposit";
    }


}
