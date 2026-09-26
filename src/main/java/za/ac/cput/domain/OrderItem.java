package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    private String orderItemId;

    private int quantity;
    private float price;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Listing listing;

    public OrderItem() {
    }

    public OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.order = builder.order;
        this.listing = builder.listing;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public Order getOrder() {
        return order;
    }

    public Listing getListing() {
        return listing;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId='" + orderItemId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", order=" + order +
                ", listing=" + listing +
                '}';
    }

    public static class Builder {

        private String orderItemId;
        private int quantity;
        private float price;
        private Order order;
        private Listing listing;

        public void setOrderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public void setListing(Listing listing) {
            this.listing = listing;
        }

        public Builder copy(OrderItem orderItem) {
            this.orderItemId = orderItem.orderItemId;
            this.quantity = orderItem.quantity;
            this.price = orderItem.price;
            this.order = orderItem.order;
            this.listing = orderItem.listing;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
