package za.ac.cput.repository.OrderRepo;

import za.ac.cput.domain.Order;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface IOrderRepository extends IRepository<Order,String> {
    List<Order> getAll();
}
