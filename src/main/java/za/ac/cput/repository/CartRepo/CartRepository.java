package za.ac.cput.repository.CartRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {

}
