package dev.roysez.financemanager.service;

import dev.roysez.financemanager.model.Transaction;

import java.io.IOException;
import java.util.List;

public interface TransactionService {

    Transaction save(Transaction entity);

    List<Transaction> save(List<Transaction> entities) throws IOException;

    Transaction findOne(Integer id);

    boolean exists(Integer id);

    List<Transaction> findAll() throws IOException;

    void delete(Integer id);

    void delete(Transaction entity);
}
