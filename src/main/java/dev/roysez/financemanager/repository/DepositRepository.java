package dev.roysez.financemanager.repository;

import dev.roysez.financemanager.model.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit,Integer> {
}
