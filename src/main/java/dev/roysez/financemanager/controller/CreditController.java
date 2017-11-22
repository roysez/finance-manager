package dev.roysez.financemanager.controller;

import dev.roysez.financemanager.model.Credit;
import dev.roysez.financemanager.model.Transaction;
import dev.roysez.financemanager.model.User;
import dev.roysez.financemanager.service.CategoryService;
import dev.roysez.financemanager.service.CreditService;
import dev.roysez.financemanager.service.TransactionService;
import dev.roysez.financemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
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

    /**
     * Генерування HTML сторінки, яка відповідає за Кредити
     * @param model - {@link Model}
     * @param error - Текст з помилкою при перенаправленні , "" - якщо немає помилок
     * @return назва View
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String creditPage(Model model, @ModelAttribute("error") String error) {

        if (!error.isEmpty()) {
            model.addAttribute("error", error);
        }

        try {

            Set<Credit> credits = creditService.findAll();
            model.addAttribute("credit", new Credit());
            model.addAttribute("credits", credits);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "credit";
    }


    /**
     * Процес обробки даних, додавання нового Кредиту
     * @param credit - обєкт Кредиту
     * @param redir -
     * @return
     */
    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public String postCredit(Credit credit,
                             RedirectAttributes redir) {


        try {

            if (credit.getTerm() < 1)
                throw new IllegalArgumentException("Term should be > 1 ) ");

            User user = userService.getUser();

            Long userBalance = user.getBalance();


            user.setBalance(userBalance + credit.getAmountToPay());


            Transaction transaction = new Transaction()
                    .setCategory(categoryService.findOneByName("Кредит"))
                    .setDate(new Date())
                    .setTrType(Transaction.TransactionType.TRANSACTION_INCOME)
                    .setDescription("Taking money on credit")
                    .setSum(credit.getAmountToPay());


            transactionService.save(transaction);

            creditService.save(credit);

            userService.saveUser(user);

            System.out.println("Credit created [" + credit.getAmountToPay() + "]");

        } catch (IOException e) {
            redir.addFlashAttribute("error", "Error while reading user information");
        } catch (IllegalStateException | IllegalArgumentException e) {
            redir.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/credits";
    }

    /**
     * Оплата частини боргу
     * @param id - унікальне значення
     * @param model - {@link Model}
     * @return JSON
     */
    @RequestMapping(value = "/{id}/charge", method = RequestMethod.POST)
    public ResponseEntity doCharge(@PathVariable Integer id, Model model) {
        try {
            Credit credit = creditService.findOne(id);
            Double charge = credit.doCharge();

            User user = userService.getUser();

            Transaction transaction = new Transaction()
                    .setCategory(categoryService.findOneByName("Кредит"))
                    .setDate(new Date())
                    .setTrType(Transaction.TransactionType.TRANSACTION_EXPENSE)
                    .setDescription("Payment of a loan")
                    .setSum(charge.longValue());

            if (user.getBalance() - charge.longValue() < 0)
                throw new IllegalStateException("You do not have enough " +
                        "money to pay for a portion of the loan, you need - " + charge);

            user.setBalance(user.getBalance() - charge.longValue());

            transactionService.save(transaction);

            userService.saveUser(user);

            creditService.update(credit);


            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Процес видалення кредиту
     * @param id - унікальне значення
     * @return - JSON
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCredit(@PathVariable Integer id) {
        creditService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
