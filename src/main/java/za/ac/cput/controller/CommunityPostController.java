package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CommunityPost;
import za.ac.cput.service.CommunityPostService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/communityPost")
public class CommunityPostController {

    private CommunityPostService service;

    @Autowired
    CommunityPostController(CommunityPostService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public CommunityPost createCommunityPost(@RequestBody CommunityPost communityPost) {
        return this.service.create(communityPost);
    }

    @GetMapping("/read/{postId}")
    public CommunityPost readCommunityPost(@PathVariable String postId) {
        return this.service.read(postId);
    }

    @PutMapping("/update")
    public CommunityPost updateCommunityPost(@RequestBody CommunityPost communityPost) {
        return this.service.update(communityPost);
    }

    @DeleteMapping("/delete/{postId}")
    public boolean deleteCommunityPost(@PathVariable String postId) {
        return this.service.delete(postId);
    }

    @GetMapping("/getAll")
    public List<CommunityPost> getAllCommunityPosts() {
        return this.service.getAll();
    }
}