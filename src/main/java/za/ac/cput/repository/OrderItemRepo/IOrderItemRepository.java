package za.ac.cput.repository.OrderItemRepo;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface IOrderItemRepository extends IRepository<OrderItem,String> {
    List<OrderItem> getAll();
}
