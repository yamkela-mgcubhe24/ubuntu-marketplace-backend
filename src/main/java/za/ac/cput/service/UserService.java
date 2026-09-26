package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepo.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {

    private UserRepository repository;

    @Autowired
    UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return this.repository.save(user);
    }

    @Override
    public User read(String userId) {
        return this.repository.findById(userId).orElse(null);
    }

    @Override
    public User update(User user) {
        return this.repository.save(user);
    }

    @Override
    public boolean delete(String userId) {
        this.repository.deleteById(userId);
        return true;
    }

    @Override
    public List<User> getAll() {
        return this.repository.findAll();
    }
}