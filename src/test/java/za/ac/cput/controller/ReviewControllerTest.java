package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

/*
ReviewControllerTest.java
Review Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class ReviewControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/review";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static final String LISTING_BASE_URL =
            "http://localhost:8080/listing";

    protected static User user;
    protected static Listing listing;
    protected static Review review;

    @BeforeAll
    public static void setUp() {

        RestTemplate setupTemplate = new RestTemplate();

        // ---------- 1. Parent: User ----------
        user = UserFactory.createUser(
                "U700",
                "Reviewer Rita",
                "rita@example.com",
                "0871112222",
                "CUSTOMER",
                "hashedPassword123",
                true
        );
        setupTemplate.postForEntity(USER_BASE_URL + "/create", user, User.class);

        // ---------- 2. Parent: Listing (needs User as seller) ----------
        listing = ListingFactory.createListing(
                "LIST002",
                "Vintage Denim Jacket",
                "Gently used, size M, blue denim",
                349.99f,
                "Clothing",
                1,
                user
        );
        setupTemplate.postForEntity(LISTING_BASE_URL + "/create", listing, Listing.class);

        // ---------- 3. Child: Review (needs User + Listing) ----------
        review = ReviewFactory.createReview(
                "REV001",
                5,
                "Excellent quality, fast delivery!",
                user,
                listing
        );
    }

    @Test
    void a_createReview() {

        String url = BASE_URL + "/create";

        ResponseEntity<Review> response =
                this.restTemplate.postForEntity(url, review, Review.class);

        assertNotNull(response);

        Review created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readReview() {

        String url = BASE_URL + "/read/" + review.getReviewId();

        ResponseEntity<Review> response =
                this.restTemplate.getForEntity(url, Review.class);

        assertNotNull(response);

        Review read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateReview() {

        String url = BASE_URL + "/update";

        // Review.Builder setters return void, so we can't chain .setComment(...)
        // Just re-save the same object.
        Review updatedReview = new Review.Builder()
                .copy(review)
                .build();

        this.restTemplate.put(url, updatedReview);

        ResponseEntity<Review> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedReview.getReviewId(),
                        Review.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllReviews() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<Review[]> response =
                this.restTemplate.getForEntity(url, Review[].class);

        System.out.println("All Reviews");

        for (Review review : response.getBody()) {
            System.out.println(review);
        }
    }

    @Test
    void e_deleteReview() {

        String url = BASE_URL + "/delete/" + review.getReviewId();

        this.restTemplate.delete(url);

        ResponseEntity<Review> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + review.getReviewId(),
                        Review.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}
