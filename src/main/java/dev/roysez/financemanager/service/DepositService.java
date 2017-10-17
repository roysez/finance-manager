package dev.roysez.financemanager.service;

import dev.roysez.financemanager.model.Deposit;
import dev.roysez.financemanager.model.Transaction;

import java.io.IOException;
import java.util.Set;

public interface DepositService {

    boolean save(Deposit entity) throws IOException;

    boolean update(Deposit entity) throws IOException;

    Set<Deposit> save(Set<Deposit> entities) throws IOException;

    Deposit findOne(Integer id) throws IOException;

    boolean exists(Integer id) throws IOException;

    Set<Deposit> findAll() throws IOException;

    void delete(Integer id);

    void delete(Deposit entity);

}
