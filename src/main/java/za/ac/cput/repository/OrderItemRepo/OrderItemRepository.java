package za.ac.cput.repository.OrderItemRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String>{

}