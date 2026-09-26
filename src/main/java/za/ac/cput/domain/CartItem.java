package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {

    @Id
    private String cartItemId;
    private int quantity;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Listing listing;

    public CartItem(){

    }
    public CartItem(Builder builder){
        this.cartItemId = builder.cartItemId;
        this.quantity = builder.quantity;
        this.cart = builder.cart;
        this.listing = builder.listing;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public Listing getListing() {
        return listing;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", quantity=" + quantity +
                ", cart=" + cart +
                ", listing=" + listing +
                '}';
    }
    public static class Builder{
        private String cartItemId;
        private int quantity;
        private Cart cart;
        private Listing listing;

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
    public Builder copy(CartItem cartItem){
        this.cartItemId = cartItem.cartItemId;
        this.quantity = cartItem.quantity;
        this.cart = cartItem.cart;
        this.listing = cartItem.listing;
        return this;
    }
    public CartItem build(){
        return new CartItem(this);
    }

    }
}
