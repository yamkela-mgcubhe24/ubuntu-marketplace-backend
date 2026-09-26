package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.User;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.factory.UserFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
PaymentServiceTest.java
Payment Module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentServiceTest {

    @Autowired
    private PaymentService service;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private static User user = UserFactory.createUser(
            "U400", "Buyer Bob", "bob@example.com",
            "0841112222", "CUSTOMER", "hashedPassword123", true);

    private static Order order = OrderFactory.createOrder(
            "ORD002", LocalDateTime.now(), 699.98f, "PENDING", user);

    private static Payment payment = PaymentFactory.createPayment(
            "PAY001", 699.98f, "Card", "COMPLETED", order);

    @Test
    void a_create() {
        userService.create(user);
        orderService.create(order);
        Payment created = this.service.create(payment);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Payment read = this.service.read(payment.getPaymentId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Payment updatedPayment = new Payment.Builder()
                .copy(payment)
                .build();

        Payment updated = this.service.update(updatedPayment);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<Payment> payments = this.service.getAll();
        assertNotNull(payments);
        System.out.println(payments);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(payment.getPaymentId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}
