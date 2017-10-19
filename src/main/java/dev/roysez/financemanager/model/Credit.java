package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Credit implements Comparable<Credit> {

    Integer id;
    Long amountToPay;
    Double paidMoney;
    CreditStatus status;
    private Integer term;

    private Integer monthPaid;

    public Credit() {
        status = CreditStatus.IN_PROCESS;
        paidMoney = 0D;
        monthPaid = 0;

    }

    @Override
    public int compareTo(Credit o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", amountToPay=" + amountToPay +
                ", paidMoney=" + paidMoney +
                ", status=" + status +
                '}';
    }

    public enum CreditStatus {
        PAID,
        IN_PROCESS;

        @Override
        public String toString() {
            return this.name().equals("PAID") ? "Paid" : "In process";
        }
    }
}
