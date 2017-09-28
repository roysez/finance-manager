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

    String firstName;

    String lastName;

    Long balance;



    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + firstName + " " + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
