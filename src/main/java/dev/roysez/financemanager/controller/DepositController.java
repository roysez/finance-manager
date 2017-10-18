package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Category;
import dev.roysez.financemanager.model.Deposit;
import dev.roysez.financemanager.model.Transaction;
import dev.roysez.financemanager.model.User;
import dev.roysez.financemanager.service.CategoryService;
import dev.roysez.financemanager.service.DepositService;
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

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@Controller
@RequestMapping(value = "/deposits")
public class DepositController {

    @Autowired
    DepositService depositService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String depositsPage(Model model, @ModelAttribute("error") String error){

        if(!error.isEmpty()){
            model.addAttribute("error",error);
        }

        try {

            Set<Deposit> deposits = depositService.findAll();
            model.addAttribute("deposit",new Deposit());
            model.addAttribute("deposits",deposits);
        } catch (Exception e) {

        }

        return "deposit";
    }

    @RequestMapping(value = {"/",""},method = RequestMethod.POST)
    public String postDeposit(Deposit deposit,
                              RedirectAttributes redir){



        try {

            if(deposit.getPercentages()< 0 || deposit.getPercentages()>100 || deposit.getTerm()<1)
                throw new IllegalArgumentException("Percentage should be [0:100] ( or term should be > 1 ) ");

            User user = userService.getUser();

            Long userBalance = user.getBalance();


            if(userBalance -deposit.getSum() < 0)
                throw new IllegalStateException("You don't have enough money to take this deposit");

            user.setBalance(userBalance-deposit.getSum());



            Transaction transaction = new Transaction()
                    .setCategory(categoryService.findOneByName("Investing money"))
                    .setDate(new Date())
                    .setTrType(Transaction.TransactionType.TRANSACTION_EXPENSE)
                    .setDescription("Put money in deposit")
                    .setSum(deposit.getSum());



            transactionService.save(transaction);

            depositService.save(deposit);

            userService.saveUser(user);

            System.out.println("Deposit created ["+ deposit.getSum()+"]");

        } catch (IOException e) {
            redir.addFlashAttribute("error","Error while reading user information");
        } catch (IllegalStateException  | IllegalArgumentException e) {
            redir.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/deposits";
    }

    @RequestMapping(value = "/{id}/charge",method = RequestMethod.POST)
    public ResponseEntity doCharge(@PathVariable Integer id, Model model){
        try {
            Deposit deposit = depositService.findOne(id);
            Long charge = deposit.doCharge();

            User user = userService.getUser();

            Transaction transaction = new Transaction()
                    .setCategory(categoryService.findOneByName("Income from deposits"))
                    .setDate(new Date())
                    .setTrType(Transaction.TransactionType.TRANSACTION_INCOME)
                    .setDescription("Profit from deposit")
                    .setSum(charge);

            user.setBalance(user.getBalance()+charge);

            transactionService.save(transaction);

            userService.saveUser(user);

            depositService.update(deposit);
            ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);


            return responseEntity;
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public  ResponseEntity deleteDeposit(@PathVariable Integer id){
        depositService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
