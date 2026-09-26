package za.ac.cput.service;

import za.ac.cput.domain.OrderItem;

import java.util.List;

public interface IOrderItemService {

    OrderItem create(OrderItem orderItem);

    OrderItem read(String orderItemId);

    OrderItem update(OrderItem orderItem);

    boolean delete(String orderItemId);

    List<OrderItem> getAll();
}