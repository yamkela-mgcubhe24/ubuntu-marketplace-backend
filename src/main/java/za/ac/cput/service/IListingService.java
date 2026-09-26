package za.ac.cput.service;

import za.ac.cput.domain.Listing;
import java.util.List;

public interface IListingService {

    Listing create(Listing listing);

    Listing read(String listingId);

    Listing update(Listing listing);

    boolean delete(String listingId);

    List<Listing> getAll();
}