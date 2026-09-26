package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Order;
import za.ac.cput.service.IOrderService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    private IOrderService service;

    @Autowired
    OrderController(IOrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return this.service.create(order);
    }

    @GetMapping("/read/{orderId}")
    public Order readOrder(@PathVariable String orderId) {
        return this.service.read(orderId);
    }

    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        return this.service.update(order);
    }

    @DeleteMapping("/delete/{orderId}")
    public boolean deleteOrder(@PathVariable String orderId) {
        return this.service.delete(orderId);
    }

    @GetMapping("/getAll")
    public List<Order> getAllOrders() {
        return this.service.getAll();
    }
}