package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.UserFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
ListingServiceTest.java
Listing Module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ListingServiceTest {

    @Autowired
    private ListingService service;

    @Autowired
    private UserService userService;

    private static User seller = UserFactory.createUser(
            "U100", "Seller Sam", "sam@example.com",
            "0831112222", "SELLER", "hashedPassword123", true);

    private static Listing listing = ListingFactory.createListing(
            "LIST001",
            "Vintage Denim Jacket",
            "Gently used, size M, blue denim",
            349.99f,
            "Clothing",
            1,
            seller
    );

    @Test
    void a_create() {
        userService.create(seller);
        Listing created = this.service.create(listing);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Listing read = this.service.read(listing.getListingId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Listing updatedListing = new Listing.Builder()
                .copy(listing)
                .build();

        Listing updated = this.service.update(updatedListing);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<Listing> listings = this.service.getAll();
        assertNotNull(listings);
        System.out.println(listings);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(listing.getListingId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}
