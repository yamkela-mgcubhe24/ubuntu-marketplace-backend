package za.ac.cput.repository;

import org.junit.jupiter.api.*;
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
import za.ac.cput.repository.CartItemRepo.CartItemRepository;
import za.ac.cput.repository.CartRepo.CartRepository;
import za.ac.cput.repository.ListingRepo.ListingRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CartItemRepositoryTest.java
CartItem module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ListingRepository listingRepository;

    private User user;
    private Cart cart;
    private Listing listing;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U001", "Chomi", "chomi@example.com",
                "0821234567", "CUSTOMER", "hashedPassword123", true);
        userRepository.save(user);

        cart = CartFactory.createCart("CART001", user);
        cartRepository.save(cart);

        listing = ListingFactory.createListing(
                "LIST001", "Vintage Denim Jacket",
                "Gently used, size M", 349.99f, "Clothing", 1, user);
        listingRepository.save(listing);

        cartItem = CartItemFactory.createCartItem(
                "CI001", 2, cart, listing);
    }

    @Test
    void a_create() {
        CartItem created = repository.save(cartItem);
        assertNotNull(created);
        assertEquals(cartItem.getCartItemId(), created.getCartItemId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(cartItem);
        CartItem read = repository.findById(cartItem.getCartItemId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(cartItem);
        CartItem updated = new CartItem.Builder()
                .copy(cartItem)
                .build();
        CartItem result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllCartItems() {
        List<CartItem> cartItems = repository.findAll();
        assertNotNull(cartItems);
        cartItems.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(cartItem);
        repository.deleteById(cartItem.getCartItemId());
        CartItem deleted = repository.findById(cartItem.getCartItemId()).orElse(null);
        assertNull(deleted);
    }
}