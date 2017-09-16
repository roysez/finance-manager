package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String categoryName;

    String description;

    public Category() {

    }

    @Override
    public String toString() {
        return "Category{" +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
