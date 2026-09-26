package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Listing;
import za.ac.cput.domain.User;
import za.ac.cput.factory.ListingFactory;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.ListingRepo.ListingRepository;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
ListingRepositoryTest.java
Listing module class
Author: [Your Name] ([Your Student Number])
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ListingRepositoryTest {

    @Autowired
    private ListingRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User seller;
    private Listing listing;

    @BeforeEach
    void setUp() {
        seller = UserFactory.createUser(
                "U100", "Seller Sam", "sam@example.com",
                "0831112222", "SELLER", "hashedPassword123", true);
        userRepository.save(seller);

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
    void a_create() {
        Listing created = repository.save(listing);
        assertNotNull(created);
        assertEquals(listing.getListingId(), created.getListingId());
        System.out.println(created);
    }

    @Test
    void b_read() {
        repository.save(listing);
        Listing read = repository.findById(listing.getListingId()).orElse(null);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        repository.save(listing);
        Listing updated = new Listing.Builder()
                .copy(listing)
                .build();
        Listing result = repository.save(updated);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    void d_getAllListings() {
        List<Listing> listings = repository.findAll();
        assertNotNull(listings);
        listings.forEach(System.out::println);
    }

    @Test
    void e_delete() {
        repository.save(listing);
        repository.deleteById(listing.getListingId());
        Listing deleted = repository.findById(listing.getListingId()).orElse(null);
        assertNull(deleted);
    }
}
