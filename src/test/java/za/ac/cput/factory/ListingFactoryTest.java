package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.User;

import static org.junit.jupiter.api.Assertions.*;

/*
ListingFactoryTest.java
Listing module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

class ListingFactoryTest {

    User seller;
    Listing listing;

    @BeforeEach
    void setUp() {
        seller = UserFactory.createUser(
                "U100", "Seller Sam", "sam@example.com",
                "0831112222", "SELLER", "hashedPassword123", true);

        listing = ListingFactory.createListing(
                "LIST001",
                "Vintage Denim Jacket",
                "Gently used, size M, blue denim",
                349.99f,
                "Clothing",
                1,
                seller
        );
    }

    @Test
    void createListing_Success() {
        assertNotNull(listing);
        assertEquals("LIST001", listing.getListingId());
        assertEquals("Vintage Denim Jacket", listing.getTitle());
        assertEquals(349.99f, listing.getPrice());
        assertEquals("Clothing", listing.getCategory());
        assertEquals(1, listing.getQuantity());
        assertEquals("U100", listing.getSeller().getUserId());
        System.out.println("created listing successfully");
    }

    @Test
    void createListing_NullListingId() {
        Listing bad = ListingFactory.createListing(
                null, "Title", "Description",
                100.0f, "Category", 1, seller);
        assertNull(bad);
        System.out.println("listing has null listingId");
    }

    @Test
    void createListing_NegativePrice() {
        Listing bad = ListingFactory.createListing(
                "LIST002", "Title", "Description",
                -50.0f, "Category", 1, seller);
        assertNull(bad);
        System.out.println("listing has negative price");
    }

    @Test
    void createListing_NullSeller() {
        Listing bad = ListingFactory.createListing(
                "LIST003", "Title", "Description",
                100.0f, "Category", 1, null);
        assertNull(bad);
        System.out.println("listing has null seller");
    }
}
