package dev.roysez.financemanager.service;

import dev.roysez.financemanager.model.Transaction;

import java.io.IOException;
import java.util.Set;

public interface TransactionService {

    boolean save(Transaction entity);

    Set<Transaction> save(Set<Transaction> entities) throws IOException;

    Transaction findOne(Integer id);

    boolean exists(Integer id);

    Set<Transaction> findAll() throws IOException;

    void delete(Integer id);

    void delete(Transaction entity);
}
