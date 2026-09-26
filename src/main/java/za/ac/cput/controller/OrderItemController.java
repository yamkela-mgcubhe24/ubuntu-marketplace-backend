package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.service.IOrderItemService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    private IOrderItemService service;

    @Autowired
    OrderItemController(IOrderItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return this.service.create(orderItem);
    }

    @GetMapping("/read/{orderItemId}")
    public OrderItem readOrderItem(@PathVariable String orderItemId) {
        return this.service.read(orderItemId);
    }

    @PutMapping("/update")
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem) {
        return this.service.update(orderItem);
    }

    @DeleteMapping("/delete/{orderItemId}")
    public boolean deleteOrderItem(@PathVariable String orderItemId) {
        return this.service.delete(orderItemId);
    }

    @GetMapping("/getAll")
    public List<OrderItem> getAllOrderItems() {
        return this.service.getAll();
    }
}