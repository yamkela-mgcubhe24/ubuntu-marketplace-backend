package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;
import za.ac.cput.factory.NotificationFactory;
import za.ac.cput.factory.UserFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
NotificationServiceTest.java
Notification Module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class NotificationServiceTest {

    @Autowired
    private NotificationService service;

    @Autowired
    private UserService userService;

    private static User user = UserFactory.createUser(
            "U500", "Notify Nancy", "nancy@example.com",
            "0851112222", "CUSTOMER", "hashedPassword123", true);

    private static Notification notification = NotificationFactory.createNotification(
            "NOT001",
            "Your order has been shipped",
            false,
            user
    );

    @Test
    void a_create() {
        userService.create(user);
        Notification created = this.service.create(notification);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Notification read = this.service.read(notification.getNotificationId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Notification updatedNotification = new Notification.Builder()
                .copy(notification)
                .build();

        Notification updated = this.service.update(updatedNotification);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<Notification> notifications = this.service.getAll();
        assertNotNull(notifications);
        System.out.println(notifications);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(notification.getNotificationId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}
