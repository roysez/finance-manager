package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@Accessors(chain = true)
public class User {

    Integer id;

    String username;

    Long balance;

    List<Transaction> transactions;

    List<Credit> credits;

    List<Deposit> deposits;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}
