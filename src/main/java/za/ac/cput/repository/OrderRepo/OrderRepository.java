package za.ac.cput.repository.OrderRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

}
