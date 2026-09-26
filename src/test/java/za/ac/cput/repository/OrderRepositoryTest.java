package za.ac.cput.repository;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.User;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.OrderRepo.OrderRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
OrderRepositoryTest.java
Order module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Order order;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U200", "Buyer Bob", "bob@example.com",
                "0841112222", "CUSTOMER", "hashedPassword123", true);
        userRepository.save(user);

        order = OrderFactory.createOrder(
                "ORD001",
                LocalDateTime.now(),
                699.98f,
                "PENDING",
                user
        );
    }

    @Test
    void a_create() {
        Order created = repository.save(order);
        assertNotNull(created);
        assertEquals(order.getOrderId(), created.getOrderId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(order);
        Order read = repository.findById(order.getOrderId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(order);
        Order updated = new Order.Builder()
                .copy(order)
                .build();
        Order result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllOrders() {
        List<Order> orders = repository.findAll();
        assertNotNull(orders);
        orders.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(order);
        repository.deleteById(order.getOrderId());
        Order deleted = repository.findById(order.getOrderId()).orElse(null);
        assertNull(deleted);
    }
}
