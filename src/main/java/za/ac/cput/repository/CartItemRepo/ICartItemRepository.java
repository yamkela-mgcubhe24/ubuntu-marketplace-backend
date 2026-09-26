package za.ac.cput.repository.CartItemRepo;

import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.CartRepo.ICartRepository;
import za.ac.cput.repository.IRepository;

import java.util.List;

public interface ICartItemRepository extends IRepository<CartItem, String> {
    List<CartItem> getAll();
}
