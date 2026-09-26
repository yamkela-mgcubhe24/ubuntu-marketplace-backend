package za.ac.cput.factory;

import za.ac.cput.domain.User;

public class UserFactory {
    public static User createUser(String userId, String name, String email, String phone, String role, String passwordHash, boolean isVerified) {
        if (userId == null || userId.isEmpty()) {
            return null;
        }
        if (name == null || name.isEmpty()) {
            return null;
        }
        if (email == null || email.isEmpty()) {
            return null;
        }
        if (phone == null || phone.length() < 10) {
            return null;
        }
        if (role == null || role.isEmpty()) {
            return null;
        }
        if (passwordHash == null || passwordHash.isEmpty()) {
            return null;
        }
        User.Builder builder = new User.Builder();
        builder.setUserId(userId);
        builder.setName(name);
        builder.setEmail(email);
        builder.setPhone(phone);
        builder.setRole(role);
        builder.setPasswordHash(passwordHash);
        builder.isVerified(isVerified);
        return builder.build();
    }
}