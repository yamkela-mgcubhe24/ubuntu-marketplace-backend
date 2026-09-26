package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.ListingRepo.ListingRepository;
import za.ac.cput.repository.ReviewRepo.ReviewRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
ReviewRepositoryTest.java
Review module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListingRepository listingRepository;

    private User user;
    private Listing listing;
    private Review review;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser(
                "U700", "Reviewer Rita", "rita@example.com",
                "0871112222", "CUSTOMER", "hashedPassword123", true);
        userRepository.save(user);

        listing = ListingFactory.createListing(
                "LIST002", "Vintage Denim Jacket",
                "Gently used, size M", 349.99f, "Clothing", 1, user);
        listingRepository.save(listing);

        review = ReviewFactory.createReview(
                "REV001",
                5,
                "Excellent quality, fast delivery!",
                user,
                listing
        );
    }

    @Test
    void a_create() {
        Review created = repository.save(review);
        assertNotNull(created);
        assertEquals(review.getReviewId(), created.getReviewId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(review);
        Review read = repository.findById(review.getReviewId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(review);
        Review updated = new Review.Builder()
                .copy(review)
                .build();
        Review result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllReviews() {
        List<Review> reviews = repository.findAll();
        assertNotNull(reviews);
        reviews.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(review);
        repository.deleteById(review.getReviewId());
        Review deleted = repository.findById(review.getReviewId()).orElse(null);
        assertNull(deleted);
    }
}