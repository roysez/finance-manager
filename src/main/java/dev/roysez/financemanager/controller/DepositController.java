package dev.roysez.financemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/deposits")
public class DepositController {

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String depositsPage(){
        return "deposit";
    }

}
