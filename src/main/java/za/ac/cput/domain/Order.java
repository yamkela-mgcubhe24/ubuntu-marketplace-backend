package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private LocalDateTime orderDate;
    private float totalAmount;
    private String status;
    @ManyToOne
    private User user;

    public Order(){

    }
    public Order (Builder builder){
        this.orderId= builder.orderId;
        this.orderDate= builder.orderDate;
        this.totalAmount = builder.totalAmount;
        this.status = builder.status;
        this.user = builder.user;

    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }

    public static class Builder{
        private String orderId;
        private LocalDateTime orderDate;
        private float totalAmount;
        private String status;
        private User user;

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
        }

        public void setTotalAmount(float totalAmount) {
            this.totalAmount = totalAmount;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setUser(User user) {
            this.user = user;
        }
        public Builder copy(Order order){
            this.orderId = order.orderId;
            this.orderDate = order.orderDate;
            this.totalAmount = order.totalAmount;
            this.status = order.status;
            this.user = order.user;
            return this;
        }
        public Order build(){
            return new Order(this);
        }
    }
}
