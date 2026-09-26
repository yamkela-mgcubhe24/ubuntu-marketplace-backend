package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderItemFactoryTest.java
OrderItem module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class OrderItemFactoryTest {

    User user;
    Order order;
    Listing listing;
    OrderItem orderItem;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U300", "Buyer Bob", "bob@example.com",
                "0841112222", "CUSTOMER", "hashedPassword123", true);

        order = OrderFactory.createOrder(
                "ORD001", LocalDateTime.now(), 349.99f, "PENDING", user);

        listing = ListingFactory.createListing(
                "LIST001", "Vintage Denim Jacket",
                "Gently used", 349.99f, "Clothing", 1, user);

        orderItem = OrderItemFactory.createOrderItem(
                "OI001", 1, 349.99f, order, listing);
    }

    @Test
    void createOrderItem_Success() {
        assertNotNull(orderItem);
        assertEquals("OI001", orderItem.getOrderItemId());
        assertEquals(1, orderItem.getQuantity());
        assertEquals(349.99f, orderItem.getPrice());
        assertEquals("ORD001", orderItem.getOrder().getOrderId());
        assertEquals("LIST001", orderItem.getListing().getListingId());
        System.out.println("created orderItem successfully");
    }

    @Test
    void createOrderItem_NullOrderItemId() {
        OrderItem bad = OrderItemFactory.createOrderItem(
                null, 1, 349.99f, order, listing);
        assertNull(bad);
        System.out.println("orderItem has null orderItemId");
    }

    @Test
    void createOrderItem_ZeroQuantity() {
        OrderItem bad = OrderItemFactory.createOrderItem(
                "OI002", 0, 349.99f, order, listing);
        assertNull(bad);
        System.out.println("orderItem has zero quantity");
    }

    @Test
    void createOrderItem_NegativePrice() {
        OrderItem bad = OrderItemFactory.createOrderItem(
                "OI003", 1, -50.0f, order, listing);
        assertNull(bad);
        System.out.println("orderItem has negative price");
    }

    @Test
    void createOrderItem_NullOrder() {
        OrderItem bad = OrderItemFactory.createOrderItem(
                "OI004", 1, 349.99f, null, listing);
        assertNull(bad);
        System.out.println("orderItem has null order");
    }

    @Test
    void createOrderItem_NullListing() {
        OrderItem bad = OrderItemFactory.createOrderItem(
                "OI005", 1, 349.99f, order, null);
        assertNull(bad);
        System.out.println("orderItem has null listing");
    }
}
