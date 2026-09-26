package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.util.Helper;

public class PaymentFactory {

    public static Payment createPayment(String paymentId,
                                        float amount,
                                        String paymentMethod,
                                        String status,
                                        Order order) {

        if (Helper.isEmptyOrNull(paymentId)) {
            return null;
        }

        if (Helper.isNumNeg(amount)) {
            return null;
        }

        if (Helper.isEmptyOrNull(paymentMethod)) {
            return null;
        }

        if (Helper.isEmptyOrNull(status)) {
            return null;
        }

        if (Helper.isValidType(order)) {
            return null;
        }

        Payment.Builder builder = new Payment.Builder();

        builder.setPaymentId(paymentId);
        builder.setAmount(amount);
        builder.setPaymentMethod(paymentMethod);
        builder.setStatus(status);
        builder.setOrder(order);

        return builder.build();
    }
}
