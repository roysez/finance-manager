package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Long sum;

    Long income;

    Integer percentages;

    DepositStatus depositStatus;

    Date date;

    String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public Deposit() {
        depositStatus = DepositStatus.IN_PROCESS;
        date = new Date();
        income = 0L;

    }

    public enum DepositStatus {
        COMPLETED,
        IN_PROCESS;

        @Override
        public String toString() {
            return this.name().equals("COMPLETED")?"Completed":"In process";
        }
    }

}
