package dev.roysez.financemanager;

import dev.roysez.financemanager.model.*;
import dev.roysez.financemanager.service.CategoryService;
import dev.roysez.financemanager.service.TransactionService;
import dev.roysez.financemanager.service.UserService;
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
									  CategoryService categoryService
	) {
		return (args) -> {
			Category category = new Category(1,"Комуналка",100L);
			Category category1 = new Category(2,"Фізруку",100L);
			Category category2 = new Category(3,"Бурса",100L);
			Category category3 = new Category(4,"Столовка",100L);


			Transaction transaction = new Transaction(1, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100, new Date(), "Витрати на комуналку",
					category);

			Transaction transaction1 = new Transaction(4, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100, new Date(), "Забашляти за фізру",
					category1);


			categoryService.save(category);
			categoryService.save(category1);
			categoryService.save(category2);
			categoryService.save(category3);

			transactionService.save(transaction);
			transactionService.save(transaction1);

			User user = new User().setBalance(100000L)
					.setFirstName("Sergiy")
					.setLastName("Balukh")
					.setId(1);

			// userService.saveUser(user);

			System.out.println(transactionService.findAll().size());

		};
	}


	public CommandLineRunner demo() {
		return (args) -> {
			// save a couple of customer)s



			Category category = new Category(1,"Комуналка",100L);
			Category category1 = new Category(2,"Фізруку",100L);
			Category category2 = new Category(3,"Бурса",100L);
			Category category3 = new Category(4,"Столовка",100L);



			new Transaction(1, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Витрати на комуналку",
					category  );
			new Transaction(2, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Забашляти за фізру",
					category1  );
			new Transaction(3, Transaction.TransactionType.TRANSACTION_INCOME,
					100,new Date(),"Стіпуха прийшла",
					category2  );
			new Transaction(4, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Сходив в кормушку",
					category2  );

			Calendar cal = Calendar.getInstance();
			cal.set(2025,10,10);
			Date date = cal.getTime();

			Credit credit = new Credit().setAmountToPay(10000L)
					.setDueDate(date);


			Deposit deposit = new Deposit().setDescription("На машину").setPercentages(10).setSum(2000L);






		};
	}
}
