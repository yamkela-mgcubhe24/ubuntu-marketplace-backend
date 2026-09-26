package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.User;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.OrderRepo.OrderRepository;
import za.ac.cput.repository.PaymentRepo.PaymentRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
PaymentRepositoryTest.java
Payment module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    private User user;
    private Order order;
    private Payment payment;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U400", "Buyer Bob", "bob@example.com",
                "0841112222", "CUSTOMER", "hashedPassword123", true);
        userRepository.save(user);

        order = OrderFactory.createOrder(
                "ORD002", LocalDateTime.now(), 699.98f, "PENDING", user);
        orderRepository.save(order);

        payment = PaymentFactory.createPayment(
                "PAY001", 699.98f, "Card", "COMPLETED", order);
    }

    @Test
    void a_create() {
        Payment created = repository.save(payment);
        assertNotNull(created);
        assertEquals(payment.getPaymentId(), created.getPaymentId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(payment);
        Payment read = repository.findById(payment.getPaymentId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(payment);
        Payment updated = new Payment.Builder()
                .copy(payment)
                .build();
        Payment result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllPayments() {
        List<Payment> payments = repository.findAll();
        assertNotNull(payments);
        payments.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(payment);
        repository.deleteById(payment.getPaymentId());
        Payment deleted = repository.findById(payment.getPaymentId()).orElse(null);
        assertNull(deleted);
    }
}
