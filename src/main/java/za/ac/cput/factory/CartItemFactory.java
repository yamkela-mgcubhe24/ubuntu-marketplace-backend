package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Listing;
import za.ac.cput.util.Helper;

public class CartItemFactory {

    public static CartItem createCartItem(String cartItemId,
                                          int quantity,
                                          Cart cart,
                                          Listing listing) {

        if (Helper.isEmptyOrNull(cartItemId)) {
            return null;
        }

        if (Helper.isNumNeg(quantity)) {
            return null;
        }

        if (Helper.isValidType(cart)) {
            return null;
        }

        if (Helper.isValidType(listing)) {
            return null;
        }

        CartItem.Builder builder = new CartItem.Builder();

        builder.setCartItemId(cartItemId);
        builder.setQuantity(quantity);
        builder.setCart(cart);
        builder.setListing(listing);

        return builder.build();
    }
}