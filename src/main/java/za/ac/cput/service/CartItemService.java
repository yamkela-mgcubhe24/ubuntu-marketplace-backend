package za.ac.cput.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.CartItem;
import za.ac.cput.repository.CartItemRepo.CartItemRepository;
import za.ac.cput.repository.CartRepo.CartRepository;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    private CartItemRepository repository;

    @Autowired
    CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public CartItem create(CartItem cartItem) {
        return this.repository.save(cartItem);
    }

    @Override
    public CartItem read(String cartItemId) {
        return this.repository.findById(cartItemId).orElse(null);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        return this.repository.save(cartItem);
    }

    @Override
    public boolean delete(String cartItemId) {
        this.repository.deleteById(cartItemId);
        return true;
    }

    @Override
    public List<CartItem> getAll() {
        return this.repository.findAll();
    }

}