package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class CartFactory {

    public static Cart createCart(String cartId, User user) {

        if (Helper.isEmptyOrNull(cartId)) {
            return null;
        }

        if (Helper.isValidType(user)) {
            return null;
        }

        Cart.Builder builder = new Cart.Builder();

        builder.setCartId(cartId);
        builder.setUser(user);

        return builder.build();
    }
}
