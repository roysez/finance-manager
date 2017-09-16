package dev.roysez.financemanager.repository;

import dev.roysez.financemanager.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);

}
