package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {

    @Id
    private String notificationId;
    private String message;
    private boolean isRead;
    @ManyToOne
    private User user;

    public Notification() {

    }

    public Notification(Builder builder) {
        this.notificationId = builder.notificationId;
        this.message = builder.message;
        this.isRead = builder.isRead;
        this.user = builder.user;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", message='" + message + '\'' +
                ", isRead=" + isRead +
                ", user=" + user +
                '}';
    }

    public static class Builder {
        private String notificationId;
        private String message;
        private boolean isRead;
        private User user;

        public void setNotificationId(String notificationId) {
            this.notificationId = notificationId;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setIsRead(boolean isRead) {
            this.isRead = isRead;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Builder copy(Notification notification) {
            this.notificationId = notification.notificationId;
            this.message = notification.message;
            this.isRead = notification.isRead;
            this.user = notification.user;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }
}
