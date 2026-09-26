package za.ac.cput.factory;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*
PaymentFactoryTest.java
Payment module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class PaymentFactoryTest {

    User user;
    Order order;
    Payment payment;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U400", "Buyer Bob", "bob@example.com",
                "0841112222", "CUSTOMER", "hashedPassword123", true);

        order = OrderFactory.createOrder(
                "ORD002", LocalDateTime.now(), 699.98f, "PENDING", user);

        payment = PaymentFactory.createPayment(
                "PAY001", 699.98f, "Card", "COMPLETED", order);
    }

    @Test
    void createPayment_Success() {
        assertNotNull(payment);
        assertEquals("PAY001", payment.getPaymentId());
        assertEquals(699.98f, payment.getAmount());
        assertEquals("Card", payment.getPaymentMethod());
        assertEquals("COMPLETED", payment.getStatus());
        assertEquals("ORD002", payment.getOrder().getOrderId());
        System.out.println("created payment successfully");
    }

    @Test
    void createPayment_NullPaymentId() {
        Payment bad = PaymentFactory.createPayment(
                null, 699.98f, "Card", "COMPLETED", order);
        assertNull(bad);
        System.out.println("payment has null paymentId");
    }

    @Test
    void createPayment_NegativeAmount() {
        Payment bad = PaymentFactory.createPayment(
                "PAY002", -50.0f, "Card", "COMPLETED", order);
        assertNull(bad);
        System.out.println("payment has negative amount");
    }

    @Test
    void createPayment_EmptyPaymentMethod() {
        Payment bad = PaymentFactory.createPayment(
                "PAY003", 699.98f, "", "COMPLETED", order);
        assertNull(bad);
        System.out.println("payment has empty paymentMethod");
    }

    @Test
    void createPayment_NullOrder() {
        Payment bad = PaymentFactory.createPayment(
                "PAY004", 699.98f, "Card", "COMPLETED", null);
        assertNull(bad);
        System.out.println("payment has null order");
    }
}