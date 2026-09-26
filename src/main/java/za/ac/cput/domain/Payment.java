package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {

    @Id
    private String paymentId;
    private float amount;
    private String paymentMethod;
    private String status;
    @ManyToOne
    private Order order;

    public Payment() {

    }

    public Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.paymentMethod = builder.paymentMethod;
        this.status = builder.status;
        this.order = builder.order;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public float getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", order=" + order +
                '}';
    }

    public static class Builder {
        private String paymentId;
        private float amount;
        private String paymentMethod;
        private String status;
        private Order order;

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public Builder copy(Payment payment) {
            this.paymentId = payment.paymentId;
            this.amount = payment.amount;
            this.paymentMethod = payment.paymentMethod;
            this.status = payment.status;
            this.order = payment.order;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}