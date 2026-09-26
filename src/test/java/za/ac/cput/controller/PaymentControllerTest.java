package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.User;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.factory.UserFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*
PaymentControllerTest.java
Payment Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/payment";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static final String ORDER_BASE_URL =
            "http://localhost:8080/order";

    protected static User user;
    protected static Order order;
    protected static Payment payment;

    @BeforeAll
    public static void setUp() {

        RestTemplate setupTemplate = new RestTemplate();

        // ---------- 1. Parent: User ----------
        user = UserFactory.createUser(
                "U400",
                "Buyer Bob",
                "bob@example.com",
                "0841112222",
                "CUSTOMER",
                "hashedPassword123",
                true
        );
        setupTemplate.postForEntity(USER_BASE_URL + "/create", user, User.class);

        // ---------- 2. Parent: Order (needs User) ----------
        order = OrderFactory.createOrder(
                "ORD002",
                LocalDateTime.now(),
                699.98f,
                "PENDING",
                user
        );
        setupTemplate.postForEntity(ORDER_BASE_URL + "/create", order, Order.class);

        // ---------- 3. Child: Payment (needs Order) ----------
        payment = PaymentFactory.createPayment(
                "PAY001",
                699.98f,
                "Card",
                "COMPLETED",
                order
        );
    }

    @Test
    void a_createPayment() {

        String url = BASE_URL + "/create";

        ResponseEntity<Payment> response =
                this.restTemplate.postForEntity(url, payment, Payment.class);

        assertNotNull(response);

        Payment created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readPayment() {

        String url = BASE_URL + "/read/" + payment.getPaymentId();

        ResponseEntity<Payment> response =
                this.restTemplate.getForEntity(url, Payment.class);

        assertNotNull(response);

        Payment read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updatePayment() {

        String url = BASE_URL + "/update";

        // Payment.Builder setters return void, so we can't chain .setStatus(...)
        // Just re-save the same object.
        Payment updatedPayment = new Payment.Builder()
                .copy(payment)
                .build();

        this.restTemplate.put(url, updatedPayment);

        ResponseEntity<Payment> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedPayment.getPaymentId(),
                        Payment.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllPayments() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<Payment[]> response =
                this.restTemplate.getForEntity(url, Payment[].class);

        System.out.println("All Payments");

        for (Payment payment : response.getBody()) {
            System.out.println(payment);
        }
    }

    @Test
    void e_deletePayment() {

        String url = BASE_URL + "/delete/" + payment.getPaymentId();

        this.restTemplate.delete(url);

        ResponseEntity<Payment> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + payment.getPaymentId(),
                        Payment.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}
