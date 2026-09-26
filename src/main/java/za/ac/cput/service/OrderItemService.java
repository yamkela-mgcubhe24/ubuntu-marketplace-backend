package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.repository.OrderItemRepo.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {

    private OrderItemRepository repository;

    @Autowired
    OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return this.repository.save(orderItem);
    }

    @Override
    public OrderItem read(String orderItemId) {
        return this.repository.findById(orderItemId).orElse(null);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return this.repository.save(orderItem);
    }

    @Override
    public boolean delete(String orderItemId) {
        this.repository.deleteById(orderItemId);
        return true;
    }

    @Override
    public List<OrderItem> getAll() {
        return this.repository.findAll();
    }
}