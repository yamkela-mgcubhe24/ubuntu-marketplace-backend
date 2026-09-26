package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CommunityPost {

    @Id
    private String postId;
    private String title;
    private String content;
    @ManyToOne
    private User user;

    public CommunityPost() {

    }

    public CommunityPost(Builder builder) {
        this.postId = builder.postId;
        this.title = builder.title;
        this.content = builder.content;
        this.user = builder.user;
    }

    public String getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "CommunityPost{" +
                "postId='" + postId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }

    public static class Builder {
        private String postId;
        private String title;
        private String content;
        private User user;

        public void setPostId(String postId) {
            this.postId = postId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Builder copy(CommunityPost communityPost) {
            this.postId = communityPost.postId;
            this.title = communityPost.title;
            this.content = communityPost.content;
            this.user = communityPost.user;
            return this;
        }

        public CommunityPost build() {
            return new CommunityPost(this);
        }
    }
}