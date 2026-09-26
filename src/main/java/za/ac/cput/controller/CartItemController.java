package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.CartItem;
import za.ac.cput.service.ICartItemService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cartItem")
public class CartItemController {

    private ICartItemService service;

    @Autowired
    CartItemController(ICartItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return this.service.create(cartItem);
    }

    @GetMapping("/read/{cartItemId}")
    public CartItem readCartItem(@PathVariable String cartItemId) {
        return this.service.read(cartItemId);
    }

    @PutMapping("/update")
    public CartItem updateCartItem(@RequestBody CartItem cartItem) {
        return this.service.update(cartItem);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public boolean deleteCartItem(@PathVariable String cartItemId) {
        return this.service.delete(cartItemId);
    }

    @GetMapping("/getAll")
    public List<CartItem> getAllCartItems() {
        return this.service.getAll();
    }
}
