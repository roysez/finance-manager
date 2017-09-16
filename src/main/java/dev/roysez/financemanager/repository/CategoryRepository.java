package dev.roysez.financemanager.repository;

import dev.roysez.financemanager.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
