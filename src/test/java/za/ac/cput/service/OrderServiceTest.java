package za.ac.cput.service;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.User;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.UserFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderServiceTest.java
Order Module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderServiceTest {

    @Autowired
    private OrderService service;

    @Autowired
    private UserService userService;

    private static User user = UserFactory.createUser(
            "U200", "Buyer Bob", "bob@example.com",
            "0841112222", "CUSTOMER", "hashedPassword123", true);

    private static Order order = OrderFactory.createOrder(
            "ORD001",
            LocalDateTime.now(),
            699.98f,
            "PENDING",
            user
    );

    @Test
    void a_create() {
        userService.create(user);
        Order created = this.service.create(order);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Order read = this.service.read(order.getOrderId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Order updatedOrder = new Order.Builder()
                .copy(order)
                .build();

        Order updated = this.service.update(updatedOrder);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<Order> orders = this.service.getAll();
        assertNotNull(orders);
        System.out.println(orders);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(order.getOrderId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}