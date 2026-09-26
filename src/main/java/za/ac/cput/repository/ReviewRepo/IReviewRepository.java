package za.ac.cput.repository.ReviewRepo;

import za.ac.cput.domain.Review;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface IReviewRepository extends IRepository<Review,String> {
    List<Review> getAll();
}
