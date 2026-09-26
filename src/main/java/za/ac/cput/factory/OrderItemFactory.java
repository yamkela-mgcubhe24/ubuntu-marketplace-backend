package za.ac.cput.factory;

import za.ac.cput.domain.Listing;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;

public class OrderItemFactory {

    public static OrderItem createOrderItem(
            String orderItemId,
            int quantity,
            float price,
            Order order,
            Listing listing) {

        if (orderItemId == null || orderItemId.isEmpty()) {
            return null;
        }

        if (quantity <= 0) {
            return null;
        }

        if (price < 0) {
            return null;
        }

        if (order == null) {
            return null;
        }

        if (listing == null) {
            return null;
        }

        OrderItem.Builder builder = new OrderItem.Builder();

        builder.setOrderItemId(orderItemId);
        builder.setQuantity(quantity);
        builder.setPrice(price);
        builder.setOrder(order);
        builder.setListing(listing);

        return builder.build();
    }
}
