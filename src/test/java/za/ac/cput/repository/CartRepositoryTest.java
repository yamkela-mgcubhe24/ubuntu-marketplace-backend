package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.CartRepo.CartRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CartRepositoryTest.java
Cart module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartRepositoryTest {

    @Autowired
    private CartRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Cart cart;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U001", "Chomi", "chomi@example.com",
                "0821234567", "CUSTOMER", "hashedPassword123", true);
        userRepository.save(user);

        cart = CartFactory.createCart("CART001", user);
    }

    @Test
    void a_create() {
        Cart created = repository.save(cart);
        assertNotNull(created);
        assertEquals(cart.getCartId(), created.getCartId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(cart);
        Cart read = repository.findById(cart.getCartId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(cart);
        Cart updated = new Cart.Builder()
                .copy(cart)
                .build();
        Cart result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllCarts() {
        List<Cart> carts = repository.findAll();
        assertNotNull(carts);
        carts.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(cart);
        repository.deleteById(cart.getCartId());
        Cart deleted = repository.findById(cart.getCartId()).orElse(null);
        assertNull(deleted);
    }
}