package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;
import za.ac.cput.factory.NotificationFactory;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

/*
NotificationControllerTest.java
Notification Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class NotificationControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/notification";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static User user;
    protected static Notification notification;

    @BeforeAll
    public static void setUp() {

        RestTemplate setupTemplate = new RestTemplate();

        // ---------- 1. Parent: User ----------
        user = UserFactory.createUser(
                "U500",
                "Notify Nancy",
                "nancy@example.com",
                "0851112222",
                "CUSTOMER",
                "hashedPassword123",
                true
        );
        setupTemplate.postForEntity(USER_BASE_URL + "/create", user, User.class);

        // ---------- 2. Child: Notification (needs User) ----------
        notification = NotificationFactory.createNotification(
                "NOT001",
                "Your order has been shipped",
                false,
                user
        );
    }

    @Test
    void a_createNotification() {

        String url = BASE_URL + "/create";

        ResponseEntity<Notification> response =
                this.restTemplate.postForEntity(url, notification, Notification.class);

        assertNotNull(response);

        Notification created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readNotification() {

        String url = BASE_URL + "/read/" + notification.getNotificationId();

        ResponseEntity<Notification> response =
                this.restTemplate.getForEntity(url, Notification.class);

        assertNotNull(response);

        Notification read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateNotification() {

        String url = BASE_URL + "/update";

        // Notification.Builder setters return void, so we can't chain .setIsRead(...)
        // Just re-save the same object.
        Notification updatedNotification = new Notification.Builder()
                .copy(notification)
                .build();

        this.restTemplate.put(url, updatedNotification);

        ResponseEntity<Notification> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedNotification.getNotificationId(),
                        Notification.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllNotifications() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<Notification[]> response =
                this.restTemplate.getForEntity(url, Notification[].class);

        System.out.println("All Notifications");

        for (Notification notification : response.getBody()) {
            System.out.println(notification);
        }
    }

    @Test
    void e_deleteNotification() {

        String url = BASE_URL + "/delete/" + notification.getNotificationId();

        this.restTemplate.delete(url);

        ResponseEntity<Notification> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + notification.getNotificationId(),
                        Notification.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}