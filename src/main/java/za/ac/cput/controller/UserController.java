package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.IUserService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService service;

    @Autowired
    UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return this.service.create(user);
    }

    @GetMapping("/read/{userId}")
    public User readUser(@PathVariable String userId) {
        return this.service.read(userId);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return this.service.update(user);
    }

    @DeleteMapping("/delete/{userId}")
    public boolean deleteUser(@PathVariable String userId) {
        return this.service.delete(userId);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return this.service.getAll();
    }
}