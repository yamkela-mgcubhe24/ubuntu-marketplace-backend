package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.factory.UserFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderItemControllerTest.java
OrderItem Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderItemControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/orderItem";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static final String ORDER_BASE_URL =
            "http://localhost:8080/order";

    protected static final String LISTING_BASE_URL =
            "http://localhost:8080/listing";

    protected static User user;
    protected static Order order;
    protected static Listing listing;
    protected static OrderItem orderItem;

    @BeforeAll
    public static void setUp() {

        RestTemplate setupTemplate = new RestTemplate();

        // ---------- 1. Parent: User ----------
        user = UserFactory.createUser(
                "U300",
                "Buyer Bob",
                "bob@example.com",
                "0841112222",
                "CUSTOMER",
                "hashedPassword123",
                true
        );
        setupTemplate.postForEntity(USER_BASE_URL + "/create", user, User.class);

        // ---------- 2. Parent: Order (needs User) ----------
        order = OrderFactory.createOrder(
                "ORD001",
                LocalDateTime.now(),
                349.99f,
                "PENDING",
                user
        );
        setupTemplate.postForEntity(ORDER_BASE_URL + "/create", order, Order.class);

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

        // ---------- 4. Child: OrderItem (needs Order + Listing) ----------
        orderItem = OrderItemFactory.createOrderItem(
                "OI001",
                1,
                349.99f,
                order,
                listing
        );
    }

    @Test
    void a_createOrderItem() {

        String url = BASE_URL + "/create";

        ResponseEntity<OrderItem> response =
                this.restTemplate.postForEntity(url, orderItem, OrderItem.class);

        assertNotNull(response);

        OrderItem created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readOrderItem() {

        String url = BASE_URL + "/read/" + orderItem.getOrderItemId();

        ResponseEntity<OrderItem> response =
                this.restTemplate.getForEntity(url, OrderItem.class);

        assertNotNull(response);

        OrderItem read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateOrderItem() {

        String url = BASE_URL + "/update";

        // OrderItem.Builder setters return void, so we can't chain .setQuantity(...)
        // Just re-save the same object.
        OrderItem updatedOrderItem = new OrderItem.Builder()
                .copy(orderItem)
                .build();

        this.restTemplate.put(url, updatedOrderItem);

        ResponseEntity<OrderItem> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedOrderItem.getOrderItemId(),
                        OrderItem.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllOrderItems() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<OrderItem[]> response =
                this.restTemplate.getForEntity(url, OrderItem[].class);

        System.out.println("All OrderItems");

        for (OrderItem orderItem : response.getBody()) {
            System.out.println(orderItem);
        }
    }

    @Test
    void e_deleteOrderItem() {

        String url = BASE_URL + "/delete/" + orderItem.getOrderItemId();

        this.restTemplate.delete(url);

        ResponseEntity<OrderItem> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + orderItem.getOrderItemId(),
                        OrderItem.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}
