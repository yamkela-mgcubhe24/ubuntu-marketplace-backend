package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

/*
CartControllerTest.java
Cart Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/cart";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static User user;
    protected static Cart cart;

    @BeforeAll
    public static void setUp() {

        // 1. Build the parent User
        user = UserFactory.createUser(
                "U001",
                "Chomi",
                "chomi@example.com",
                "0821234567",
                "CUSTOMER",
                "hashedPassword123",
                true
        );

        // 2. Save the User first (Cart has @ManyToOne User — FK must exist)
        RestTemplate setupTemplate = new RestTemplate();
        setupTemplate.postForEntity(USER_BASE_URL + "/create", user, User.class);

        // 3. Build the Cart referencing the saved User
        cart = CartFactory.createCart(
                "CART001",
                user
        );
    }

    @Test
    void a_createCart() {

        String url = BASE_URL + "/create";

        ResponseEntity<Cart> response =
                this.restTemplate.postForEntity(url, cart, Cart.class);

        assertNotNull(response);

        Cart created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readCart() {

        String url = BASE_URL + "/read/" + cart.getCartId();

        ResponseEntity<Cart> response =
                this.restTemplate.getForEntity(url, Cart.class);

        assertNotNull(response);

        Cart read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateCart() {

        String url = BASE_URL + "/update";

        Cart updatedCart = new Cart.Builder()
                .copy(cart)
                .build();

        this.restTemplate.put(url, updatedCart);

        ResponseEntity<Cart> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedCart.getCartId(),
                        Cart.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllCarts() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<Cart[]> response =
                this.restTemplate.getForEntity(url, Cart[].class);

        System.out.println("All Carts");

        for (Cart cart : response.getBody()) {
            System.out.println(cart);
        }
    }

    @Test
    void e_deleteCart() {

        String url = BASE_URL + "/delete/" + cart.getCartId();

        this.restTemplate.delete(url);

        ResponseEntity<Cart> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + cart.getCartId(),
                        Cart.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}