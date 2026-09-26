package za.ac.cput.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CommunityPost;
import za.ac.cput.repository.CommunityPostRepo.CommunityPostRepository;

import java.util.List;

@Service
public class CommunityPostService implements ICommunityPostService {

    private CommunityPostRepository repository;

    @Autowired
    CommunityPostService(CommunityPostRepository repository) {
        this.repository = repository;
    }

    @Override
    public CommunityPost create(CommunityPost communityPost) {
        return this.repository.save(communityPost);
    }

    @Override
    public CommunityPost read(String postId) {
        return this.repository.findById(postId).orElse(null);
    }

    @Override
    public CommunityPost update(CommunityPost communityPost) {
        return this.repository.save(communityPost);
    }

    @Override
    public boolean delete(String postId) {
        this.repository.deleteById(postId);
        return true;
    }

    @Override
    public List<CommunityPost> getAll() {
        return this.repository.findAll();
    }

}
