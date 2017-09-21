package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    TransactionType trType;

    Integer sum;

    Date date;

    String description;

    @OneToOne
    Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public Transaction() {

    }

    public enum TransactionType {
        TRANSACTION_EXPENSE,
        TRANSACTION_INCOME ;

        @Override
        public String toString() {
            return this.name().equals("TRANSACTION_EXPENSE")?"Expense":"Income";
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", trType=" + trType +
                ", sum=" + sum +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}'+'\n';
    }
}
