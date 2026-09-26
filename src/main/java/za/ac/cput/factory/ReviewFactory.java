package za.ac.cput.factory;

import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;

public class ReviewFactory {
    public static Review createReview(String reviewId,
                                      int rating,
                                      String comment,
                                      User user,
                                      Listing listing) {
        if (reviewId == null || reviewId.isEmpty()) {
            return null;
        }
        if (rating < 1 || rating > 5) {
            return null;
        }
        if (comment == null || comment.isEmpty()) {
            return null;
        }
        if (user == null) {
            return null;
        }
        if (listing == null) {
            return null;
        }
        Review.Builder builder = new Review.Builder();
        builder.setReviewId(reviewId);
        builder.setRating(rating);
        builder.setComment(comment);
        builder.setUser(user);
        builder.setListing(listing);
        return builder.build();
    }
}
