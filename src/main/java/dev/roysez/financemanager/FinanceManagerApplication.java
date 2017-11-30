package dev.roysez.financemanager;

import dev.roysez.financemanager.model.*;
import dev.roysez.financemanager.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@SpringBootApplication
@ComponentScan
public class FinanceManagerApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(FinanceManagerApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FinanceManagerApplication.class);
    }


    @Bean
    public CommandLineRunner demoFile(TransactionService transactionService, UserService userService,
                                      CategoryService categoryService, DepositService depositService,
                                      CreditService creditService
    ) {
        return (args) -> {

            Category category = new Category(1, "Комунальні послуги", 0);
            Category category4 = new Category(5, "Кредит", 0);
            Category category5 = new Category(6, "Депозит", 0);

            Transaction transaction = new Transaction(1, Transaction.TransactionType.TRANSACTION_EXPENSE,
                    100L, new Date(), "Витрати на комунальні послуги",
                    category);


            categoryService.save(category);
            categoryService.save(category5);
            categoryService.save(category4);

            if (transactionService.findAll().size() == 0) {
                transactionService.save(transaction);
            }

            User user = new User().setBalance(1500L)
                    .setFirstName("Сергій")
                    .setLastName("Балух")
                    .setId(1);

            //  userService.saveUser(user);


            Deposit deposit = new Deposit().setSum(3000L)
                    .setTerm(5)
                    .setPercentages(10)
                    .setId(0)
                    .setDescription("Депозит/ПриватБанк")
                    .setDepositStatus(Deposit.DepositStatus.IN_PROCESS)
                    .setIncome(0L);

            if (depositService.findAll().size() == 0) {
                depositService.save(deposit);
            }


            Credit credit = new Credit().setAmountToPay(1000L).setTerm(12).setDescription("Кредит/ПриватБанк");

            if (creditService.findAll().size() == 0) {
                creditService.save(credit);
            }
        };
    }


}
