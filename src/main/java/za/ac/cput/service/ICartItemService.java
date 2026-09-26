package za.ac.cput.service;

import za.ac.cput.domain.CartItem;

import java.util.List;

public interface ICartItemService {

    CartItem create(CartItem cartItem);

    CartItem read(String cartItemId);

    CartItem update(CartItem cartItem);

    boolean delete(String cartItemId);

    List<CartItem> getAll();
}