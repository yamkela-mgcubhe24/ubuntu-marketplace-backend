package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.CommunityPost;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

/*
CommunityPostFactoryTest.java
CommunityPost module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class CommunityPostFactoryTest {

    User user;
    CommunityPost communityPost;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U600", "Poster Pete", "pete@example.com",
                "0861112222", "CUSTOMER", "hashedPassword123", true);

        communityPost = CommunityPostFactory.createCommunityPost(
                "POST001",
                "Welcome!",
                "Hello everyone, excited to be here.",
                user
        );
    }

    @Test
    void createCommunityPost_Success() {
        assertNotNull(communityPost);
        assertEquals("POST001", communityPost.getPostId());
        assertEquals("Welcome!", communityPost.getTitle());
        assertEquals("Hello everyone, excited to be here.", communityPost.getContent());
        assertEquals("U600", communityPost.getUser().getUserId());
        System.out.println("created communityPost successfully");
    }

    @Test
    void createCommunityPost_NullPostId() {
        CommunityPost bad = CommunityPostFactory.createCommunityPost(
                null, "Title", "Content", user);
        assertNull(bad);
        System.out.println("communityPost has null postId");
    }

    @Test
    void createCommunityPost_EmptyTitle() {
        CommunityPost bad = CommunityPostFactory.createCommunityPost(
                "POST002", "", "Content", user);
        assertNull(bad);
        System.out.println("communityPost has empty title");
    }

    @Test
    void createCommunityPost_NullUser() {
        CommunityPost bad = CommunityPostFactory.createCommunityPost(
                "POST003", "Title", "Content", null);
        assertNull(bad);
        System.out.println("communityPost has null user");
    }
}
