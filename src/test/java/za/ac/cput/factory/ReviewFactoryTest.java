package za.ac.cput.factory;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

/*
ReviewFactoryTest.java
Review module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class ReviewFactoryTest {

    User user;
    Listing listing;
    Review review;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U700", "Reviewer Rita", "rita@example.com",
                "0871112222", "CUSTOMER", "hashedPassword123", true);

        listing = ListingFactory.createListing(
                "LIST002", "Vintage Denim Jacket",
                "Gently used, size M", 349.99f, "Clothing", 1, user);

        review = ReviewFactory.createReview(
                "REV001",
                5,
                "Excellent quality, fast delivery!",
                user,
                listing
        );
    }

    @Test
    void createReview_Success() {
        assertNotNull(review);
        assertEquals("REV001", review.getReviewId());
        assertEquals(5, review.getRating());
        assertEquals("Excellent quality, fast delivery!", review.getComment());
        assertEquals("U700", review.getUser().getUserId());
        assertEquals("LIST002", review.getListing().getListingId());
        System.out.println("created review successfully");
    }

    @Test
    void createReview_NullReviewId() {
        Review bad = ReviewFactory.createReview(
                null, 5, "Comment", user, listing);
        assertNull(bad);
        System.out.println("review has null reviewId");
    }

    @Test
    void createReview_InvalidRatingLow() {
        Review bad = ReviewFactory.createReview(
                "REV002", 0, "Comment", user, listing);
        assertNull(bad);
        System.out.println("review has rating below 1");
    }

    @Test
    void createReview_InvalidRatingHigh() {
        Review bad = ReviewFactory.createReview(
                "REV003", 6, "Comment", user, listing);
        assertNull(bad);
        System.out.println("review has rating above 5");
    }

    @Test
    void createReview_EmptyComment() {
        Review bad = ReviewFactory.createReview(
                "REV004", 5, "", user, listing);
        assertNull(bad);
        System.out.println("review has empty comment");
    }

    @Test
    void createReview_NullUser() {
        Review bad = ReviewFactory.createReview(
                "REV005", 5, "Comment", null, listing);
        assertNull(bad);
        System.out.println("review has null user");
    }

    @Test
    void createReview_NullListing() {
        Review bad = ReviewFactory.createReview(
                "REV006", 5, "Comment", user, null);
        assertNull(bad);
        System.out.println("review has null listing");
    }
}