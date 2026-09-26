package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String passwordHash;
    private boolean isVerified;

    public User(){

}
    public User(Builder builder){
    this.userId = builder.userId;
    this.name = builder.name;
    this.email = builder.email;
    this.phone = builder.phone;
    this.role = builder.role;
    this.passwordHash = builder.passwordHash;
    this.isVerified = builder.isVerified;

    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }


    public boolean isVerified() {
        return isVerified;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", isVerified=" + isVerified +
                '}';
    }

    public static class Builder{
        private String userId;
        private String name;
        private String email;
        private String phone;
        private String role;
        private String passwordHash;
        private boolean isVerified;


        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }


        public void isVerified(boolean isVerified) {
            this.isVerified = isVerified;
        }

        public Builder copy(User user){
            this.userId = user.userId;
            this.name = user.name;
            this.email = user.email;
            this.phone = user.phone;
            this.role = user.role;
            this.passwordHash = user.passwordHash;
            this.isVerified = user.isVerified;
            return this;
        }
        public User build(){
            return new User(this);

        }
    }



}
