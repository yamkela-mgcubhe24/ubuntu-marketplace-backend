package za.ac.cput.service;


import za.ac.cput.domain.Notification;

import java.util.List;

public interface INotificationService {

    Notification create(Notification notification);

    Notification read(String notificationId);

    Notification update(Notification notification);

    boolean delete(String notificationId);

    List<Notification> getAll();
}
