package za.ac.cput.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Listing;
import za.ac.cput.repository.ListingRepo.ListingRepository;

import java.util.List;

@Service
public class ListingService implements IListingService {

    private ListingRepository repository;

    @Autowired
    ListingService(ListingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Listing create(Listing listing) {
        return this.repository.save(listing);
    }

    @Override
    public Listing read(String listingId) {
        return this.repository.findById(listingId).orElse(null);
    }

    @Override
    public Listing update(Listing listing) {
        return this.repository.save(listing);
    }

    @Override
    public boolean delete(String listingId) {
        this.repository.deleteById(listingId);
        return true;
    }

    @Override
    public List<Listing> getAll() {
        return this.repository.findAll();
    }

}