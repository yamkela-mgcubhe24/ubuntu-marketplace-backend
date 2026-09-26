package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.factory.UserFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ReviewServiceTest {

    @Autowired
    private ReviewService service;

    @Autowired
    private UserService userService;

    @Autowired
    private ListingService listingService;

    private static User user = UserFactory.createUser(
            "U700", "Reviewer Rita", "rita@example.com",
            "0871112222", "CUSTOMER", "hashedPassword123", true);

    private static Listing listing = ListingFactory.createListing(
            "LIST002", "Vintage Denim Jacket",
            "Gently used, size M", 349.99f, "Clothing", 1, user);

    private static Review review = ReviewFactory.createReview(
            "REV001",
            5,
            "Excellent quality, fast delivery!",
            user,
            listing
    );

    @Test
    void a_create() {
        userService.create(user);
        listingService.create(listing);
        Review created = this.service.create(review);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Review read = this.service.read(review.getReviewId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Review updatedReview = new Review.Builder()
                .copy(review)
                .build();

        Review updated = this.service.update(updatedReview);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<Review> reviews = this.service.getAll();
        assertNotNull(reviews);
        System.out.println(reviews);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(review.getReviewId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}
