package za.ac.cput.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.PaymentRepo.PaymentRepository;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private PaymentRepository repository;

    @Autowired
    PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment create(Payment payment) {
        return this.repository.save(payment);
    }

    @Override
    public Payment read(String paymentId) {
        return this.repository.findById(paymentId).orElse(null);
    }

    @Override
    public Payment update(Payment payment) {
        return this.repository.save(payment);
    }

    @Override
    public boolean delete(String paymentId) {
        this.repository.deleteById(paymentId);
        return true;
    }

    @Override
    public List<Payment> getAll() {
        return this.repository.findAll();
    }

}