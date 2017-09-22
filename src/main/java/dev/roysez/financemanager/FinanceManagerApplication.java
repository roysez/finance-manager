package dev.roysez.financemanager;

import dev.roysez.financemanager.model.*;
import dev.roysez.financemanager.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Calendar;
import java.util.Date;

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


	public CommandLineRunner demo(TransactionRepository repository,
								  CategoryRepository categoryRepository,
								  UserRepository userRepository,
								  CreditRepository creditRepository,
								  DepositRepository depositRepository
								  ) {
		return (args) -> {
			// save a couple of customer)s
			User user = new User().setBalance(100000L).setUsername("roysez");

			userRepository.save(user);

			Category category = new Category(1,"Комуналка",100L);
			Category category1 = new Category(2,"Фізруку",100L);
			Category category2 = new Category(3,"Бурса",100L);
			Category category3 = new Category(4,"Столовка",100L);

			categoryRepository.save(category);
			categoryRepository.save(category1);
			categoryRepository.save(category2);
			categoryRepository.save(category3);

			repository.save(new Transaction(1, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Витрати на комуналку",
					category,user));
			repository.save(new Transaction(2, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Забашляти за фізру",
					category1,user));
			repository.save(new Transaction(3, Transaction.TransactionType.TRANSACTION_INCOME,
					100,new Date(),"Стіпуха прийшла",
					category2,user));
			repository.save(new Transaction(4, Transaction.TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Сходив в кормушку",
					category2,user));

			Calendar cal = Calendar.getInstance();
			cal.set(2025,10,10);
			Date date = cal.getTime();

			Credit credit = new Credit().setAmountToPay(10000L)
					.setDueDate(date).setUser(user);

			creditRepository.save(credit);

			Deposit deposit = new Deposit().setDescription("На машину").setPercentages(10).setSum(2000L).setUser(user);

			depositRepository.save(deposit);




		};
	}
}
