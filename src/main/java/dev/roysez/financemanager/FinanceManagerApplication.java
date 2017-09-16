package dev.roysez.financemanager;

import dev.roysez.financemanager.enums.TransactionType;
import dev.roysez.financemanager.model.User;
import dev.roysez.financemanager.model.Category;
import dev.roysez.financemanager.model.Transaction;
import dev.roysez.financemanager.repository.CategoryRepository;
import dev.roysez.financemanager.repository.TransactionRepository;
import dev.roysez.financemanager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@SpringBootApplication
@ComponentScan
public class FinanceManagerApplication {

	private static final Logger log = LoggerFactory.getLogger(FinanceManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FinanceManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TransactionRepository repository,
								  CategoryRepository categoryRepository,
								  UserRepository userRepository) {
		return (args) -> {
			// save a couple of customer)s
			User user = new User();
			user.setBalance(100000L).setUsername("Sergiy");
			userRepository.save(user);

			Category category = new Category(1,"Csdas","test");
			categoryRepository.save(category);
			repository.save(new Transaction(1, TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Description",
					category,user));
			repository.save(new Transaction(2,TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Description" ,
					category,user));

			repository.save(new Transaction(3,TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Description" ,
					category,user));
			repository.save(new Transaction(4,TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Description" ,
					category,user));

			repository.save(new Transaction(5,TransactionType.TRANSACTION_EXPENSE,
					100,new Date(),"Description" ,
					category,user));


			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Transaction transaction : repository.findAll()) {
				log.info(transaction.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Transaction transaction = repository.findOne(1);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(transaction.toString());
			log.info("");


		};
	}
}
