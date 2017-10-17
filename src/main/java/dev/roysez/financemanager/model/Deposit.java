package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Deposit implements Comparable<Deposit> {

        @Override
        public int compareTo(Deposit o) {
            return this.id-o.id;
        }

    private Integer id;

    private Long sum;

    private Long income;

    private Integer percentages;

    private DepositStatus depositStatus;

    private Date date;

    private String description;



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
