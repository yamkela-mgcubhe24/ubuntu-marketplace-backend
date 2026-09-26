package za.ac.cput.repository.NotificationRepo;

import za.ac.cput.domain.Notification;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface INotificationRepository extends IRepository<Notification, String> {
    List<Notification> getAll();
}
