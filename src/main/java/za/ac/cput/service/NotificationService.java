package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Notification;
import za.ac.cput.repository.NotificationRepo.NotificationRepository;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

    private NotificationRepository repository;

    @Autowired
    NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Notification create(Notification notification) {
        return this.repository.save(notification);
    }

    @Override
    public Notification read(String notificationId) {
        return this.repository.findById(notificationId).orElse(null);
    }

    @Override
    public Notification update(Notification notification) {
        return this.repository.save(notification);
    }

    @Override
    public boolean delete(String notificationId) {
        this.repository.deleteById(notificationId);
        return true;
    }

    @Override
    public List<Notification> getAll() {
        return this.repository.findAll();
    }

}
