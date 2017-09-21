package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String username;

    Long balance;

    @OneToMany(mappedBy = "user")
    List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    List<Credit> credits;

    @OneToMany(mappedBy = "user")
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
