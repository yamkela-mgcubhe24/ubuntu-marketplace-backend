package za.ac.cput.service;
import za.ac.cput.domain.CommunityPost;

import java.util.List;

public interface ICommunityPostService {

    CommunityPost create(CommunityPost communityPost);

    CommunityPost read(String postId);

    CommunityPost update(CommunityPost communityPost);

    boolean delete(String postId);

    List<CommunityPost> getAll();
}