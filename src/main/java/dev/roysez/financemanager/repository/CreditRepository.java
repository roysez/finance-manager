package dev.roysez.financemanager.repository;

import dev.roysez.financemanager.model.Credit;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepository extends CrudRepository<Credit,Integer> {
}
