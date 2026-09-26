package za.ac.cput.factory;

import za.ac.cput.domain.Notification;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class NotificationFactory {

    public static Notification createNotification(String notificationId,
                                                  String message,
                                                  boolean isRead,
                                                  User user) {

        if (Helper.isEmptyOrNull(notificationId)) {
            return null;
        }

        if (Helper.isEmptyOrNull(message)) {
            return null;
        }

        if (Helper.isValidType(user)) {
            return null;
        }

        Notification.Builder builder = new Notification.Builder();

        builder.setNotificationId(notificationId);
        builder.setMessage(message);
        builder.setIsRead(isRead);
        builder.setUser(user);

        return builder.build();
    }
}