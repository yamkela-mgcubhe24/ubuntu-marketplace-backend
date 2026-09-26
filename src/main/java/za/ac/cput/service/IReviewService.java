package za.ac.cput.service;

import za.ac.cput.domain.Review;

import java.util.List;

public interface IReviewService {

    Review create(Review review);

    Review read(String reviewId);

    Review update(Review review);

    boolean delete(String reviewId);

    List<Review> getAll();
}
