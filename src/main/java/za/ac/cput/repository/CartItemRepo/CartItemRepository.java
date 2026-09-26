package za.ac.cput.repository.CartItemRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,String> {

}
