package dev.roysez.financemanager.service;

import dev.roysez.financemanager.model.Category;
import dev.roysez.financemanager.model.Transaction;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {

    boolean save(Category entity) throws IOException;

    Set<Category> save(Set<Category> entities) throws IOException;

    Category findOne(Integer id) throws IOException;

    Category findOneByName(String sC) throws IOException;

    boolean exists(Integer id) throws IOException;

    Set<Category> findAll() throws IOException;
}
