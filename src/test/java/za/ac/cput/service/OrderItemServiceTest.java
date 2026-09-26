package za.ac.cput.service;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.factory.UserFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderItemServiceTest.java
OrderItem Module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderItemServiceTest {

    @Autowired
    private OrderItemService service;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ListingService listingService;

    private static User user = UserFactory.createUser(
            "U300", "Buyer Bob", "bob@example.com",
            "0841112222", "CUSTOMER", "hashedPassword123", true);

    private static Order order = OrderFactory.createOrder(
            "ORD001", LocalDateTime.now(), 349.99f, "PENDING", user);

    private static Listing listing = ListingFactory.createListing(
            "LIST001", "Vintage Denim Jacket",
            "Gently used", 349.99f, "Clothing", 1, user);

    private static OrderItem orderItem = OrderItemFactory.createOrderItem(
            "OI001", 1, 349.99f, order, listing);

    @Test
    void a_create() {
        userService.create(user);
        orderService.create(order);
        listingService.create(listing);
        OrderItem created = this.service.create(orderItem);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        OrderItem read = this.service.read(orderItem.getOrderItemId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        OrderItem updatedOrderItem = new OrderItem.Builder()
                .copy(orderItem)
                .build();

        OrderItem updated = this.service.update(updatedOrderItem);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<OrderItem> orderItems = this.service.getAll();
        assertNotNull(orderItems);
        System.out.println(orderItems);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(orderItem.getOrderItemId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}
