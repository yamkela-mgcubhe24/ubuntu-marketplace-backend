package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.CartService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService service;

    @Autowired
    CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Cart createCart(@RequestBody Cart cart) {
        return this.service.create(cart);
    }

    @GetMapping("/read/{cartId}")
    public Cart readCart(@PathVariable String cartId) {
        return this.service.read(cartId);
    }

    @PutMapping("/update")
    public Cart updateCart(@RequestBody Cart cart) {
        return this.service.update(cart);
    }

    @DeleteMapping("/delete/{cartId}")
    public boolean deleteCart(@PathVariable String cartId) {
        return this.service.delete(cartId);
    }

    @GetMapping("/getAll")
    public List<Cart> getAllCarts() {
        return this.service.getAll();
    }
}
