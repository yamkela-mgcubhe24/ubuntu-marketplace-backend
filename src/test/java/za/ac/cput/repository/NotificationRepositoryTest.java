package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;
import za.ac.cput.factory.NotificationFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.NotificationRepo.NotificationRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
NotificationRepositoryTest.java
Notification module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Notification notification;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U500", "Notify Nancy", "nancy@example.com",
                "0851112222", "CUSTOMER", "hashedPassword123", true);
        userRepository.save(user);

        notification = NotificationFactory.createNotification(
                "NOT001",
                "Your order has been shipped",
                false,
                user
        );
    }

    @Test
    void a_create() {
        Notification created = repository.save(notification);
        assertNotNull(created);
        assertEquals(notification.getNotificationId(), created.getNotificationId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(notification);
        Notification read = repository.findById(notification.getNotificationId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(notification);
        Notification updated = new Notification.Builder()
                .copy(notification)
                .build();
        Notification result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllNotifications() {
        List<Notification> notifications = repository.findAll();
        assertNotNull(notifications);
        notifications.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(notification);
        repository.deleteById(notification.getNotificationId());
        Notification deleted = repository.findById(notification.getNotificationId()).orElse(null);
        assertNull(deleted);
    }
}
