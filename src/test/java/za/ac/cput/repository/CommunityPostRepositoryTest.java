package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CommunityPost;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CommunityPostFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.CommunityPostRepo.CommunityPostRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CommunityPostRepositoryTest.java
CommunityPost module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CommunityPostRepositoryTest {

    @Autowired
    private CommunityPostRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private CommunityPost communityPost;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U600", "Poster Pete", "pete@example.com",
                "0861112222", "CUSTOMER", "hashedPassword123", true);
        userRepository.save(user);

        communityPost = CommunityPostFactory.createCommunityPost(
                "POST001",
                "Welcome!",
                "Hello everyone, excited to be here.",
                user
        );
    }

    @Test
    void a_create() {
        CommunityPost created = repository.save(communityPost);
        assertNotNull(created);
        assertEquals(communityPost.getPostId(), created.getPostId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(communityPost);
        CommunityPost read = repository.findById(communityPost.getPostId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(communityPost);
        CommunityPost updated = new CommunityPost.Builder()
                .copy(communityPost)
                .build();
        CommunityPost result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllCommunityPosts() {
        List<CommunityPost> posts = repository.findAll();
        assertNotNull(posts);
        posts.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(communityPost);
        repository.deleteById(communityPost.getPostId());
        CommunityPost deleted = repository.findById(communityPost.getPostId()).orElse(null);
        assertNull(deleted);
    }
}
