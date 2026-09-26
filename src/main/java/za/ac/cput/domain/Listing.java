package za.ac.cput.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Listing {

    @Id
    private String listingId;
    private String title;
    private String description;
    private float price;
    private String category;
    private int quantity;
      @ManyToOne
    private User seller;

    public Listing(){

    }
    public Listing(Builder builder){
        this.listingId = builder.listingId;
        this.title = builder.title;
        this.description = builder.description;
        this.price = builder.price;
        this.category = builder.category;
        this.quantity = builder.quantity;
        this.seller = builder.seller;

    }

    public String getListingId() {
        return listingId;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getSeller() {
        return seller;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "listingId='" + listingId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", seller=" + seller +
                '}';
    }

    public static class Builder {
        private String listingId;
        private String title;
        private String description;
        private float price;
        private String category;
        private int quantity;
        private User seller;

        public void setListingId(String listingId) {
            this.listingId = listingId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void setSeller(User seller) {
            this.seller = seller;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public Builder copy(Listing listing){
            this.listingId = listing.listingId;
            this.title = listing.title;
            this.description = listing.description;
            this.price = listing.price;
            this.category = listing.category;
            this.quantity = listing.quantity;
            this.seller = listing.seller;
            return this;
        }
        public Listing build(){
            return new Listing(this);
        }
    }


}
