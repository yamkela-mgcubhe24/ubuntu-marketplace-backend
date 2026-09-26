package za.ac.cput.factory;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

/*
CartItemFactoryTest.java
CartItem module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class CartItemFactoryTest {

    User user;
    Cart cart;
    Listing listing;
    CartItem cartItem;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U001", "Chomi", "chomi@example.com",
                "0821234567", "CUSTOMER", "hashedPassword123", true);

        cart = CartFactory.createCart("CART001", user);

        listing = ListingFactory.createListing(
                "LIST001", "Vintage Denim Jacket",
                "Gently used, size M", 349.99f, "Clothing", 1, user);

        cartItem = CartItemFactory.createCartItem(
                "CI001", 2, cart, listing);
    }

    @Test
    void createCartItem_Success() {
        assertNotNull(cartItem);
        assertEquals("CI001", cartItem.getCartItemId());
        assertEquals(2, cartItem.getQuantity());
        assertEquals("CART001", cartItem.getCart().getCartId());
        assertEquals("LIST001", cartItem.getListing().getListingId());
        System.out.println("created cartItem successfully");
    }

    @Test
    void createCartItem_NullCartItemId() {
        CartItem bad = CartItemFactory.createCartItem(
                null, 2, cart, listing);
        assertNull(bad);
        System.out.println("cartItem has null cartItemId");
    }

    @Test
    void createCartItem_NegativeQuantity() {
        CartItem bad = CartItemFactory.createCartItem(
                "CI002", -1, cart, listing);
        assertNull(bad);
        System.out.println("cartItem has negative quantity");
    }

    @Test
    void createCartItem_NullCart() {
        CartItem bad = CartItemFactory.createCartItem(
                "CI003", 2, null, listing);
        assertNull(bad);
        System.out.println("cartItem has null cart");
    }

    @Test
    void createCartItem_NullListing() {
        CartItem bad = CartItemFactory.createCartItem(
                "CI004", 2, cart, null);
        assertNull(bad);
        System.out.println("cartItem has null listing");
    }
}
