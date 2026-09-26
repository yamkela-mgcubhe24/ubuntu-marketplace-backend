package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.UserFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CartItemServiceTest.java
CartItem Module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemServiceTest {

    @Autowired
    private CartItemService service;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ListingService listingService;

    private static User user = UserFactory.createUser(
            "U001", "Chomi", "chomi@example.com",
            "0821234567", "CUSTOMER", "hashedPassword123", true);

    private static Cart cart = CartFactory.createCart("CART001", user);

    private static Listing listing = ListingFactory.createListing(
            "LIST001", "Vintage Denim Jacket",
            "Gently used, size M", 349.99f, "Clothing", 1, user);

    private static CartItem cartItem = CartItemFactory.createCartItem(
            "CI001", 2, cart, listing);

    @Test
    void a_create() {
        userService.create(user);
        cartService.create(cart);
        listingService.create(listing);
        CartItem created = this.service.create(cartItem);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        CartItem read = this.service.read(cartItem.getCartItemId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        CartItem updatedCartItem = new CartItem.Builder()
                .copy(cartItem)
                .build();

        CartItem updated = this.service.update(updatedCartItem);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<CartItem> cartItems = this.service.getAll();
        assertNotNull(cartItems);
        System.out.println(cartItems);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(cartItem.getCartItemId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}
