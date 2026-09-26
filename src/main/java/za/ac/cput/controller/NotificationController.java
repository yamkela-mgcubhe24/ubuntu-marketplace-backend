package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Notification;
import za.ac.cput.service.NotificationService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService service;

    @Autowired
    NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Notification createNotification(@RequestBody Notification notification) {
        return this.service.create(notification);
    }

    @GetMapping("/read/{notificationId}")
    public Notification readNotification(@PathVariable String notificationId) {
        return this.service.read(notificationId);
    }

    @PutMapping("/update")
    public Notification updateNotification(@RequestBody Notification notification) {
        return this.service.update(notification);
    }

    @DeleteMapping("/delete/{notificationId}")
    public boolean deleteNotification(@PathVariable String notificationId) {
        return this.service.delete(notificationId);
    }

    @GetMapping("/getAll")
    public List<Notification> getAllNotifications() {
        return this.service.getAll();
    }
}