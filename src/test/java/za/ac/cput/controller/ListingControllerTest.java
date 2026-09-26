package za.ac.cput.controller;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

/*
ListingControllerTest.java
Listing Controller test class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class ListingControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/listing";

    protected static final String USER_BASE_URL =
            "http://localhost:8080/user";

    protected static User seller;
    protected static Listing listing;

    @BeforeAll
    public static void setUp() {

        RestTemplate setupTemplate = new RestTemplate();

        // ---------- 1. Parent: User (seller) ----------
        seller = UserFactory.createUser(
                "U100",
                "Seller Sam",
                "sam@example.com",
                "0831112222",
                "SELLER",
                "hashedPassword123",
                true
        );
        setupTemplate.postForEntity(USER_BASE_URL + "/create", seller, User.class);

        // ---------- 2. Child: Listing (needs User as seller) ----------
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
    void a_createListing() {

        String url = BASE_URL + "/create";

        ResponseEntity<Listing> response =
                this.restTemplate.postForEntity(url, listing, Listing.class);

        assertNotNull(response);

        Listing created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readListing() {

        String url = BASE_URL + "/read/" + listing.getListingId();

        ResponseEntity<Listing> response =
                this.restTemplate.getForEntity(url, Listing.class);

        assertNotNull(response);

        Listing read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateListing() {

        String url = BASE_URL + "/update";

        // Listing.Builder setters return void, so we can't chain .setPrice(...)
        // Just re-save the same object.
        Listing updatedListing = new Listing.Builder()
                .copy(listing)
                .build();

        this.restTemplate.put(url, updatedListing);

        ResponseEntity<Listing> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedListing.getListingId(),
                        Listing.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllListings() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<Listing[]> response =
                this.restTemplate.getForEntity(url, Listing[].class);

        System.out.println("All Listings");

        for (Listing listing : response.getBody()) {
            System.out.println(listing);
        }
    }

    @Test
    void e_deleteListing() {

        String url = BASE_URL + "/delete/" + listing.getListingId();

        this.restTemplate.delete(url);

        ResponseEntity<Listing> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + listing.getListingId(),
                        Listing.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}
