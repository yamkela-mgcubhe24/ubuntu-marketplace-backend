package za.ac.cput.repository;

import org.junit.jupiter.api.*;
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
import za.ac.cput.repository.ListingRepo.ListingRepository;
import za.ac.cput.repository.OrderItemRepo.OrderItemRepository;
import za.ac.cput.repository.OrderRepo.OrderRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderItemRepositoryTest.java
OrderItem module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ListingRepository listingRepository;

    private User user;
    private Order order;
    private Listing listing;
    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U300", "Buyer Bob", "bob@example.com",
                "0841112222", "CUSTOMER", "hashedPassword123", true);
        userRepository.save(user);

        order = OrderFactory.createOrder(
                "ORD001", LocalDateTime.now(), 349.99f, "PENDING", user);
        orderRepository.save(order);

        listing = ListingFactory.createListing(
                "LIST001", "Vintage Denim Jacket",
                "Gently used", 349.99f, "Clothing", 1, user);
        listingRepository.save(listing);

        orderItem = OrderItemFactory.createOrderItem(
                "OI001", 1, 349.99f, order, listing);
    }

    @Test
    void a_create() {
        OrderItem created = repository.save(orderItem);
        assertNotNull(created);
        assertEquals(orderItem.getOrderItemId(), created.getOrderItemId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(orderItem);
        OrderItem read = repository.findById(orderItem.getOrderItemId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(orderItem);
        OrderItem updated = new OrderItem.Builder()
                .copy(orderItem)
                .build();
        OrderItem result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllOrderItems() {
        List<OrderItem> orderItems = repository.findAll();
        assertNotNull(orderItems);
        orderItems.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(orderItem);
        repository.deleteById(orderItem.getOrderItemId());
        OrderItem deleted = repository.findById(orderItem.getOrderItemId()).orElse(null);
        assertNull(deleted);
    }
}
