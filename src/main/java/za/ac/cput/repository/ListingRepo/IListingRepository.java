package za.ac.cput.repository.ListingRepo;

import za.ac.cput.domain.Listing;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface IListingRepository extends IRepository<Listing,String> {
    List<Listing> getAll();
}

