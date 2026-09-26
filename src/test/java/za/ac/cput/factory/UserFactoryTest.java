package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

/*
CartFactoryTest.java
Cart module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class CartFactoryTest {

    User user;
    Cart cart;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U001", "Chomi", "chomi@example.com",
                "0821234567", "CUSTOMER", "hashedPassword123", true);

        cart = CartFactory.createCart("CART001", user);
    }

    @Test
    void createCart_Success() {
        assertNotNull(cart);
        assertEquals("CART001", cart.getCartId());
        assertEquals("U001", cart.getUser().getUserId());
        System.out.println("created cart successfully");
    }

    @Test
    void createCart_NullCartId() {
        Cart bad = CartFactory.createCart(null, user);
        assertNull(bad);
        System.out.println("cart has null cartId");
    }

    @Test
    void createCart_NullUser() {
        Cart bad = CartFactory.createCart("CART002", null);
        assertNull(bad);
        System.out.println("cart has null user");
    }
}