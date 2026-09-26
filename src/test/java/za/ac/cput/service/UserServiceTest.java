package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class UserServiceTest {

    @Autowired
    private UserService service;

    private static User user = UserFactory.createUser(
            "U001",
            "Yamkela",
            "Yamkelam@gmail.com",
            "0821234567",
            "CUSTOMER",
            "hashedPassword123",
            true
    );

    @Test
    void a_create() {
        User created = this.service.create(user);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        User read = this.service.read(user.getUserId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        User updatedUser = new User.Builder()
                .copy(user)
                .build();

        User updated = this.service.update(updatedUser);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<User> users = this.service.getAll();
        assertNotNull(users);
        System.out.println(users);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(user.getUserId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}
