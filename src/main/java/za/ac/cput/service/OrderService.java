package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Order;
import za.ac.cput.repository.OrderRepo.OrderRepository;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private OrderRepository repository;

    @Autowired
    OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order create(Order order) {
        return this.repository.save(order);
    }

    @Override
    public Order read(String orderId) {
        return this.repository.findById(orderId).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return this.repository.save(order);
    }

    @Override
    public boolean delete(String orderId) {
        this.repository.deleteById(orderId);
        return true;
    }

    @Override
    public List<Order> getAll() {
        return this.repository.findAll();
    }
}
