package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class OrderFactory {

    public static Order createOrder(String orderId,
                                    LocalDateTime orderDate,
                                    float totalAmount,
                                    String status,
                                    User user) {

        if (Helper.isEmptyOrNull(orderId) ||
                orderDate == null ||
                Helper.isEmptyOrNull(status)) {
            return null;
        }

        if (Helper.isNumNeg(totalAmount)) {
            return null;
        }

        if (Helper.isValidType(user)) {
            return null;
        }

        Order.Builder builder = new Order.Builder();

        builder.setOrderId(orderId);
        builder.setOrderDate(orderDate);
        builder.setTotalAmount(totalAmount);
        builder.setStatus(status);
        builder.setUser(user);

        return builder.build();
    }
}
