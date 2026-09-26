package za.ac.cput.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Cart;
import za.ac.cput.repository.CartRepo.CartRepository;

import java.util.List;

@Service
public class CartService implements ICartService {

    private CartRepository repository;

    @Autowired
    CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cart create(Cart cart) {
        return this.repository.save(cart);
    }

    @Override
    public Cart read(String cartId) {
        return this.repository.findById(cartId).orElse(null);
    }

    @Override
    public Cart update(Cart cart) {
        return this.repository.save(cart);
    }

    @Override
    public boolean delete(String cartId) {
        this.repository.deleteById(cartId);
        return true;
    }

    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }

}