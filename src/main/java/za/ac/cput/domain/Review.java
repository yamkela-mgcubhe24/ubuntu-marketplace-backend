package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    private String reviewId;
    private int rating;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Listing listing;

    public Review (){

    }

    public Review (Builder builder){
        this.reviewId = builder.reviewId;
        this.rating= builder.rating;
        this.comment = builder.comment;
        this.user = builder.user;
        this.listing = builder.listing;

    }

    public String getReviewId() {
        return reviewId;
    }

    public int getRating() {
        return rating;
    }

    public User getUser() {
        return user;
    }

    public Listing getListing() {
        return listing;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", listing=" + listing +
                '}';

    }
    public static class Builder{
        private String reviewId;
        private int rating;
        private String comment;
        private User user;
        private Listing listing;

        public void setReviewId(String reviewId) {
            this.reviewId = reviewId;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public void setListing(Listing listing) {
            this.listing = listing;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setUser(User user) {
            this.user = user;
        }
        public Builder copy(Review review){
            this.reviewId = review.reviewId;
            this.rating = review.rating;
            this.listing = review.listing;
            this.comment = review.comment;
            this.user = review.user;
            return this;
        }
        public Review build(){
            return new Review(this);
        }
    }
}
