package za.ac.cput.factory;

import za.ac.cput.domain.Listing;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class ListingFactory {

    public static Listing createListing(String listingId,
                                        String title,
                                        String description,
                                        float price,
                                        String category,
                                        int quantity,
                                        User seller) {

        if (Helper.isEmptyOrNull(listingId) ||
                Helper.isEmptyOrNull(title) ||
                Helper.isEmptyOrNull(description) ||
                Helper.isEmptyOrNull(category)) {
            return null;
        }

        if (Helper.isNumNeg(price)) {
            return null;
        }

        if (quantity < 0) {
            return null;
        }

        if (Helper.isValidType(seller)) {
            return null;
        }

        Listing.Builder builder = new Listing.Builder();

        builder.setListingId(listingId);
        builder.setTitle(title);
        builder.setDescription(description);
        builder.setPrice(price);
        builder.setCategory(category);
        builder.setQuantity(quantity);
        builder.setSeller(seller);

        return builder.build();
    }
}

