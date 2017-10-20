package dev.roysez.financemanager.service;

import dev.roysez.financemanager.model.Credit;

import java.io.IOException;
import java.util.Set;

public interface CreditService {

    boolean save(Credit entity) throws IOException;

    boolean update(Credit entity) throws IOException;

    Set<Credit> save(Set<Credit> entities) throws IOException;

    Credit findOne(Integer id) throws IOException;

    boolean exists(Integer id) throws IOException;

    Set<Credit> findAll() throws IOException;

    void delete(Integer id);

    void delete(Credit entity);
}
