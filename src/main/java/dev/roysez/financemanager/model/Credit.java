package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Long amountToPay;

    Long paidMoney;

    Date date;

    Date dueDate;

    CreditStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public Credit() {
        status = CreditStatus.IN_PROCESS;
        date = new Date();
        paidMoney = 0L;
    }

    public enum CreditStatus {
        PAID,
        IN_PROCESS;

        @Override
        public String toString() {
            return this.name().equals("PAID")?"Paid":"In process";
        }
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", amountToPay=" + amountToPay +
                ", paidMoney=" + paidMoney +
                ", date=" + date +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}