package za.ac.cput.repository;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
UserRepositoryTest.java
User module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private User user;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U001",
                "Chomi",
                "chomi@example.com",
                "0821234567",
                "CUSTOMER",
                "hashedPassword123",
                true
        );
    }

    @Test
    void a_create() {
        User created = repository.save(user);
        assertNotNull(created);
        assertEquals(user.getUserId(), created.getUserId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(user);
        User read = repository.findById(user.getUserId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(user);
        User updated = new User.Builder()
                .copy(user)
                .build();
        User result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllUsers() {
        List<User> users = repository.findAll();
        assertNotNull(users);
        users.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(user);
        repository.deleteById(user.getUserId());
        User deleted = repository.findById(user.getUserId()).orElse(null);
        assertNull(deleted);
    }
}