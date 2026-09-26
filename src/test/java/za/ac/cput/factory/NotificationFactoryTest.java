package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

/*
NotificationFactoryTest.java
Notification module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class NotificationFactoryTest {

    User user;
    Notification notification;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U500", "Notify Nancy", "nancy@example.com",
                "0851112222", "CUSTOMER", "hashedPassword123", true);

        notification = NotificationFactory.createNotification(
                "NOT001",
                "Your order has been shipped",
                false,
                user
        );
    }

    @Test
    void createNotification_Success() {
        assertNotNull(notification);
        assertEquals("NOT001", notification.getNotificationId());
        assertEquals("Your order has been shipped", notification.getMessage());
        assertFalse(notification.isRead());
        assertEquals("U500", notification.getUser().getUserId());
        System.out.println("created notification successfully");
    }

    @Test
    void createNotification_NullNotificationId() {
        Notification bad = NotificationFactory.createNotification(
                null, "Message", false, user);
        assertNull(bad);
        System.out.println("notification has null notificationId");
    }

    @Test
    void createNotification_EmptyMessage() {
        Notification bad = NotificationFactory.createNotification(
                "NOT002", "", false, user);
        assertNull(bad);
        System.out.println("notification has empty message");
    }

    @Test
    void createNotification_NullUser() {
        Notification bad = NotificationFactory.createNotification(
                "NOT003", "Message", false, null);
        assertNull(bad);
        System.out.println("notification has null user");
    }
}
