package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.factory.CartItemFactory;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

/*
CartItemControllerTest.java
CartItem Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CartItemControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/cartItem";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static final String CART_BASE_URL =
            "http://localhost:8080/cart";

    protected static final String LISTING_BASE_URL =
            "http://localhost:8080/listing";

    protected static User user;
    protected static Cart cart;
    protected static Listing listing;
    protected static CartItem cartItem;

    @BeforeAll
    public static void setUp() {

        RestTemplate setupTemplate = new RestTemplate();

        // ---------- 1. Parent: User ----------
        user = UserFactory.createUser(
                "U001",
                "Chomi",
                "chomi@example.com",
                "0821234567",
                "CUSTOMER",
                "hashedPassword123",
                true
        );
        setupTemplate.postForEntity(USER_BASE_URL + "/create", user, User.class);

        // ---------- 2. Parent: Cart (needs User) ----------
        cart = CartFactory.createCart("CART001", user);
        setupTemplate.postForEntity(CART_BASE_URL + "/create", cart, Cart.class);

        // ---------- 3. Parent: Listing (needs User as seller) ----------
        listing = ListingFactory.createListing(
                "LIST001",
                "Vintage Denim Jacket",
                "Gently used, size M, blue denim",
                349.99f,
                "Clothing",
                1,
                user
        );
        setupTemplate.postForEntity(LISTING_BASE_URL + "/create", listing, Listing.class);

        // ---------- 4. Child: CartItem (needs Cart + Listing) ----------
        cartItem = CartItemFactory.createCartItem(
                "CI001",
                2,
                cart,
                listing
        );
    }

    @Test
    void a_createCartItem() {

        String url = BASE_URL + "/create";

        ResponseEntity<CartItem> response =
                this.restTemplate.postForEntity(url, cartItem, CartItem.class);

        assertNotNull(response);

        CartItem created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readCartItem() {

        String url = BASE_URL + "/read/" + cartItem.getCartItemId();

        ResponseEntity<CartItem> response =
                this.restTemplate.getForEntity(url, CartItem.class);

        assertNotNull(response);

        CartItem read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateCartItem() {

        String url = BASE_URL + "/update";

        // CartItem.Builder setters return void, so we can't chain .setQuantity(...)
        // Just re-save the same object.
        CartItem updatedCartItem = new CartItem.Builder()
                .copy(cartItem)
                .build();

        this.restTemplate.put(url, updatedCartItem);

        ResponseEntity<CartItem> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedCartItem.getCartItemId(),
                        CartItem.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllCartItems() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<CartItem[]> response =
                this.restTemplate.getForEntity(url, CartItem[].class);

        System.out.println("All CartItems");

        for (CartItem cartItem : response.getBody()) {
            System.out.println(cartItem);
        }
    }

    @Test
    void e_deleteCartItem() {

        String url = BASE_URL + "/delete/" + cartItem.getCartItemId();

        this.restTemplate.delete(url);

        ResponseEntity<CartItem> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + cartItem.getCartItemId(),
                        CartItem.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}