package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.CommunityPost;
import za.ac.cput.domain.User;
import za.ac.cput.factory.CommunityPostFactory;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

/*
CommunityPostControllerTest.java
CommunityPost Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CommunityPostControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/communityPost";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static User user;
    protected static CommunityPost communityPost;

    @BeforeAll
    public static void setUp() {

        RestTemplate setupTemplate = new RestTemplate();

        // ---------- 1. Parent: User ----------
        user = UserFactory.createUser(
                "U600",
                "Poster Pete",
                "pete@example.com",
                "0861112222",
                "CUSTOMER",
                "hashedPassword123",
                true
        );
        setupTemplate.postForEntity(USER_BASE_URL + "/create", user, User.class);

        // ---------- 2. Child: CommunityPost (needs User) ----------
        communityPost = CommunityPostFactory.createCommunityPost(
                "POST001",
                "Welcome!",
                "Hello everyone, excited to be here.",
                user
        );
    }

    @Test
    void a_createCommunityPost() {

        String url = BASE_URL + "/create";

        ResponseEntity<CommunityPost> response =
                this.restTemplate.postForEntity(url, communityPost, CommunityPost.class);

        assertNotNull(response);

        CommunityPost created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readCommunityPost() {

        String url = BASE_URL + "/read/" + communityPost.getPostId();

        ResponseEntity<CommunityPost> response =
                this.restTemplate.getForEntity(url, CommunityPost.class);

        assertNotNull(response);

        CommunityPost read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateCommunityPost() {

        String url = BASE_URL + "/update";

        // CommunityPost.Builder setters return void, so we can't chain .setTitle(...)
        // Just re-save the same object.
        CommunityPost updatedPost = new CommunityPost.Builder()
                .copy(communityPost)
                .build();

        this.restTemplate.put(url, updatedPost);

        ResponseEntity<CommunityPost> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedPost.getPostId(),
                        CommunityPost.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllCommunityPosts() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<CommunityPost[]> response =
                this.restTemplate.getForEntity(url, CommunityPost[].class);

        System.out.println("All CommunityPosts");

        for (CommunityPost post : response.getBody()) {
            System.out.println(post);
        }
    }

    @Test
    void e_deleteCommunityPost() {

        String url = BASE_URL + "/delete/" + communityPost.getPostId();

        this.restTemplate.delete(url);

        ResponseEntity<CommunityPost> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + communityPost.getPostId(),
                        CommunityPost.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}