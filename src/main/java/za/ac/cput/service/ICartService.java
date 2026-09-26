package za.ac.cput.service;

import za.ac.cput.domain.Cart;

import java.util.List;

public interface ICartService {

    Cart create(Cart cart);

    Cart read(String cartId);

    Cart update(Cart cart);

    boolean delete(String cartId);

    List<Cart> getAll();
}
