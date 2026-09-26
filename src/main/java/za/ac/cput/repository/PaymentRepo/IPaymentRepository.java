package za.ac.cput.repository.PaymentRepo;

import za.ac.cput.domain.Payment;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface IPaymentRepository extends IRepository<Payment, String>{
List<Payment> getAll();
}
