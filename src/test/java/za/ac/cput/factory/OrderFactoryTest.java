package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderFactoryTest.java
Order module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class OrderFactoryTest {

    User user;
    Order order;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U200", "Buyer Bob", "bob@example.com",
                "0841112222", "CUSTOMER", "hashedPassword123", true);

        order = OrderFactory.createOrder(
                "ORD001",
                LocalDateTime.now(),
                699.98f,
                "PENDING",
                user
        );
    }

    @Test
    void createOrder_Success() {
        assertNotNull(order);
        assertEquals("ORD001", order.getOrderId());
        assertEquals(699.98f, order.getTotalAmount());
        assertEquals("PENDING", order.getStatus());
        assertEquals("U200", order.getUser().getUserId());
        System.out.println("created order successfully");
    }

    @Test
    void createOrder_NullOrderId() {
        Order bad = OrderFactory.createOrder(
                null, LocalDateTime.now(), 100.0f, "PENDING", user);
        assertNull(bad);
        System.out.println("order has null orderId");
    }

    @Test
    void createOrder_NullOrderDate() {
        Order bad = OrderFactory.createOrder(
                "ORD002", null, 100.0f, "PENDING", user);
        assertNull(bad);
        System.out.println("order has null orderDate");
    }

    @Test
    void createOrder_NegativeAmount() {
        Order bad = OrderFactory.createOrder(
                "ORD003", LocalDateTime.now(), -50.0f, "PENDING", user);
        assertNull(bad);
        System.out.println("order has negative totalAmount");
    }

    @Test
    void createOrder_NullUser() {
        Order bad = OrderFactory.createOrder(
                "ORD004", LocalDateTime.now(), 100.0f, "PENDING", null);
        assertNull(bad);
        System.out.println("order has null user");
    }
}