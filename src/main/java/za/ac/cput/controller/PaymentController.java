package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Payment;
import za.ac.cput.service.PaymentService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService service;

    @Autowired
    PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Payment createPayment(@RequestBody Payment payment) {
        return this.service.create(payment);
    }

    @GetMapping("/read/{paymentId}")
    public Payment readPayment(@PathVariable String paymentId) {
        return this.service.read(paymentId);
    }

    @PutMapping("/update")
    public Payment updatePayment(@RequestBody Payment payment) {
        return this.service.update(payment);
    }

    @DeleteMapping("/delete/{paymentId}")
    public boolean deletePayment(@PathVariable String paymentId) {
        return this.service.delete(paymentId);
    }

    @GetMapping("/getAll")
    public List<Payment> getAllPayments() {
        return this.service.getAll();
    }
}