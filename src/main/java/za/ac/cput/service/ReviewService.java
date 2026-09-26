package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Review;
import za.ac.cput.repository.ReviewRepo.ReviewRepository;

import java.util.List;

@Service
public class ReviewService implements IReviewService {

    private ReviewRepository repository;

    @Autowired
    ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Review create(Review review) {
        return this.repository.save(review);
    }

    @Override
    public Review read(String reviewId) {
        return this.repository.findById(reviewId).orElse(null);
    }

    @Override
    public Review update(Review review) {
        return this.repository.save(review);
    }

    @Override
    public boolean delete(String reviewId) {
        this.repository.deleteById(reviewId);
        return true;
    }

    @Override
    public List<Review> getAll() {
        return this.repository.findAll();
    }
}
