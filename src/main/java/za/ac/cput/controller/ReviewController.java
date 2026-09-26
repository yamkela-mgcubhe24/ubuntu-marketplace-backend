package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.service.IReviewService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/review")
public class ReviewController {

    private IReviewService service;

    @Autowired
    ReviewController(IReviewService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Review createReview(@RequestBody Review review) {
        return this.service.create(review);
    }

    @GetMapping("/read/{reviewId}")
    public Review readReview(@PathVariable String reviewId) {
        return this.service.read(reviewId);
    }

    @PutMapping("/update")
    public Review updateReview(@RequestBody Review review) {
        return this.service.update(review);
    }

    @DeleteMapping("/delete/{reviewId}")
    public boolean deleteReview(@PathVariable String reviewId) {
        return this.service.delete(reviewId);
    }

    @GetMapping("/getAll")
    public List<Review> getAllReviews() {
        return this.service.getAll();
    }
}