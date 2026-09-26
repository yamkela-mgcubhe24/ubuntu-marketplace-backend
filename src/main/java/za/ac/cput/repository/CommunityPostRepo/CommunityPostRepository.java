package za.ac.cput.repository.CommunityPostRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CommunityPost;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, String> {

}
