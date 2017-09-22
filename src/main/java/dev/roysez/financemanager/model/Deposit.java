package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Deposit {



    Long sum;

    Long income;

    Integer percentages;

    DepositStatus depositStatus;

    Date date;

    String description;

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
