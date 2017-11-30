package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Credit implements Comparable<Credit> {

    private Integer id;
    private Long amountToPay;
    private Double paidMoney;
    private CreditStatus status;
    private Integer term;
    private String description;
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

    public Double doCharge() {

        if (!status.equals(CreditStatus.PAID)) {
            Double amount = (double) amountToPay / (double) term;
            paidMoney += amount;
            monthPaid++;
            if (term.equals(monthPaid))
                status = CreditStatus.PAID;

            return amount;
        } else throw new IllegalStateException("Deposit is already ended");
    }

    public boolean checkIfCompleted() {
        return status.equals(CreditStatus.PAID);
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
