package dev.roysez.financemanager;

import dev.roysez.financemanager.model.*;
import dev.roysez.financemanager.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;

@SpringBootApplication
@ComponentScan
public class FinanceManagerApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(FinanceManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FinanceManagerApplication.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FinanceManagerApplication.class);
	}


	@Bean
	public CommandLineRunner demoFile(TransactionService transactionService,UserService userService,
									  CategoryService categoryService,DepositService depositService,
									  CreditService creditService
	) {
		return (args) -> {

			Category category = new Category(1,"Комуналка",100);
			Category category1 = new Category(2,"Фізруку",100);
			Category category2 = new Category(3,"Бурса",100);
			Category category3 = new Category(4,"Столовка",100);


			Transaction transaction = new Transaction(1, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100L, new Date(), "Витрати на комуналку",
					category);

			Transaction transaction1 = new Transaction(4, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100L, new Date(), "Забашляти за фізру",
					category1);


			categoryService.save(category);
			categoryService.save(category1);
			categoryService.save(category2);
			categoryService.save(category3);

			if(transactionService.findAll().size()==0) {
				transactionService.save(transaction);
				transactionService.save(transaction1);
			}

			User user = new User().setBalance(100000L)
					.setFirstName("Sergiy")
					.setLastName("Balukh")
					.setId(1);

			// userService.saveUser(user);


			Deposit deposit = new Deposit().setSum(3000L)
					.setPercentages(10)
					.setId(0)
					.setDescription("Privat Bank Deposit")
					.setDepositStatus(Deposit.DepositStatus.IN_PROCESS)
					.setIncome(0L);

			if(depositService.findAll().size()==0) {
				 depositService.save(deposit);
			}



			Credit credit = new Credit().setAmountToPay(1000L).setTerm(12);

			if(creditService.findAll().size()==0) {
				creditService.save(credit);
			}
		};
	}



}
