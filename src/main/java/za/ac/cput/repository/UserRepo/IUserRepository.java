package za.ac.cput.repository.UserRepo;

import za.ac.cput.domain.User;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface IUserRepository extends IRepository<User,String> {
    List<User> getAll();
}
