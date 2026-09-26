package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Listing;
import za.ac.cput.service.ListingService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/listing")
public class ListingController {

    private ListingService service;

    @Autowired
    ListingController(ListingService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Listing createListing(@RequestBody Listing listing) {
        return this.service.create(listing);
    }

    @GetMapping("/read/{listingId}")
    public Listing readListing(@PathVariable String listingId) {
        return this.service.read(listingId);
    }

    @PutMapping("/update")
    public Listing updateListing(@RequestBody Listing listing) {
        return this.service.update(listing);
    }

    @DeleteMapping("/delete/{listingId}")
    public boolean deleteListing(@PathVariable String listingId) {
        return this.service.delete(listingId);
    }

    @GetMapping("/getAll")
    public List<Listing> getAllListings() {
        return this.service.getAll();
    }
}
