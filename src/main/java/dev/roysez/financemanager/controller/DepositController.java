package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Deposit;
import dev.roysez.financemanager.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping(value = "/deposits")
public class DepositController {

    @Autowired
    DepositService depositService;

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String depositsPage(Model model){

        try {

            Set<Deposit> deposits = depositService.findAll();
            model.addAttribute("deposits",deposits);
        } catch (Exception e) {

        }

        return "deposit";
    }

}
