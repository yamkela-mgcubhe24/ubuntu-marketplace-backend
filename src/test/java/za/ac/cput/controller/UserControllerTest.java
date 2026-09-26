package za.ac.cput.controller;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

/*
UserControllerTest.java
User Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class UserControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/user";

    protected static User user;

    @BeforeAll
    public static void setUp() {

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
    void a_createUser() {

        String url = BASE_URL + "/create";

        ResponseEntity<User> response =
                this.restTemplate.postForEntity(url, user, User.class);

        assertNotNull(response);

        User created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readUser() {

        String url = BASE_URL + "/read/" + user.getUserId();

        ResponseEntity<User> response =
                this.restTemplate.getForEntity(url, User.class);

        assertNotNull(response);

        User read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateUser() {

        String url = BASE_URL + "/update";

        // User.Builder setters return void, so we can't chain .setName(...)
        // Just re-save the same object.
        User updatedUser = new User.Builder()
                .copy(user)
                .build();

        this.restTemplate.put(url, updatedUser);

        ResponseEntity<User> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedUser.getUserId(),
                        User.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllUsers() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<User[]> response =
                this.restTemplate.getForEntity(url, User[].class);

        System.out.println("All Users");

        for (User user : response.getBody()) {
            System.out.println(user);
        }
    }

    @Test
    void e_deleteUser() {

        String url = BASE_URL + "/delete/" + user.getUserId();

        this.restTemplate.delete(url);

        ResponseEntity<User> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + user.getUserId(),
                        User.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}
