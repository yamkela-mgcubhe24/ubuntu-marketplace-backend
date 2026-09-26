package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CommunityPost;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CommunityPostFactory;
import za.ac.cput.factory.UserFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CommunityPostServiceTest.java
CommunityPost Module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CommunityPostServiceTest {

    @Autowired
    private CommunityPostService service;

    @Autowired
    private UserService userService;

    private static User user = UserFactory.createUser(
            "U600", "Poster Pete", "pete@example.com",
            "0861112222", "CUSTOMER", "hashedPassword123", true);

    private static CommunityPost communityPost = CommunityPostFactory.createCommunityPost(
            "POST001",
            "Welcome!",
            "Hello everyone, excited to be here.",
            user
    );

    @Test
    void a_create() {
        userService.create(user);
        CommunityPost created = this.service.create(communityPost);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        CommunityPost read = this.service.read(communityPost.getPostId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        CommunityPost updatedPost = new CommunityPost.Builder()
                .copy(communityPost)
                .build();

        CommunityPost updated = this.service.update(updatedPost);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<CommunityPost> posts = this.service.getAll();
        assertNotNull(posts);
        System.out.println(posts);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(communityPost.getPostId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}