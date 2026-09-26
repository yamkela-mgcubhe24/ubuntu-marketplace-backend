package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Cart {
    @Id
    private String cartId;

    @ManyToOne
    private User user;

    public Cart(){

    }
    public Cart(Builder builder){
        this.cartId = builder.cartId;
        this.user = builder.user;
    }

    public String getCartId() {
        return cartId;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId='" + cartId + '\'' +
                ", user=" + user +
                '}';
    }
    public static class Builder {
        private String cartId;
        private User user;

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Builder copy(Cart cart){
            this.cartId = cart.cartId;
            this.user = cart.user;
            return this;
        }

        public Cart build(){
            return new Cart(this);
        }



    }
}
