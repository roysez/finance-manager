package dev.roysez.financemanager.repository;

import dev.roysez.financemanager.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {


}
