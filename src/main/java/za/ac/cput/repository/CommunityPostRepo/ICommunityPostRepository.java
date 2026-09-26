package za.ac.cput.repository.CommunityPostRepo;

import za.ac.cput.domain.CommunityPost;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface ICommunityPostRepository extends IRepository<CommunityPost, String> {
    List<CommunityPost> getAll();
}
