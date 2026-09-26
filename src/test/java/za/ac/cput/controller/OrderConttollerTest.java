package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.User;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.UserFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderControllerTest.java
Order Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/order";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static User user;
    protected static Order order;

    @BeforeAll
    public static void setUp() {

        RestTemplate setupTemplate = new RestTemplate();

        // ---------- 1. Parent: User ----------
        user = UserFactory.createUser(
                "U200",
                "Buyer Bob",
                "bob@example.com",
                "0841112222",
                "CUSTOMER",
                "hashedPassword123",
                true
        );
        setupTemplate.postForEntity(USER_BASE_URL + "/create", user, User.class);

        // ---------- 2. Child: Order (needs User) ----------
        order = OrderFactory.createOrder(
                "ORD001",
                LocalDateTime.now(),
                699.98f,
                "PENDING",
                user
        );
    }

    @Test
    void a_createOrder() {

        String url = BASE_URL + "/create";

        ResponseEntity<Order> response =
                this.restTemplate.postForEntity(url, order, Order.class);

        assertNotNull(response);

        Order created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readOrder() {

        String url = BASE_URL + "/read/" + order.getOrderId();

        ResponseEntity<Order> response =
                this.restTemplate.getForEntity(url, Order.class);

        assertNotNull(response);

        Order read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateOrder() {

        String url = BASE_URL + "/update";

        // Order.Builder setters return void, so we can't chain .setStatus(...)
        // Just re-save the same object.
        Order updatedOrder = new Order.Builder()
                .copy(order)
                .build();

        this.restTemplate.put(url, updatedOrder);

        ResponseEntity<Order> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedOrder.getOrderId(),
                        Order.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllOrders() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<Order[]> response =
                this.restTemplate.getForEntity(url, Order[].class);

        System.out.println("All Orders");

        for (Order order : response.getBody()) {
            System.out.println(order);
        }
    }

    @Test
    void e_deleteOrder() {

        String url = BASE_URL + "/delete/" + order.getOrderId();

        this.restTemplate.delete(url);

        ResponseEntity<Order> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + order.getOrderId(),
                        Order.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}
