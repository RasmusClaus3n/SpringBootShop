package com.rasmusclausen.storeproject.controller;

import com.rasmusclausen.storeproject.entity.CartItem;
import com.rasmusclausen.storeproject.entity.Product;
import com.rasmusclausen.storeproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

@Controller
@SessionAttributes({"cart", "totalSum", "cartSize"})
@RequestMapping("cart")
public class CartController {

    @Autowired
    ProductService productService;

    // Used to format totalSum
    private static final Formatter formatter = new Formatter();

    @GetMapping("")
    public String showCart(Model model) {
        if (!model.containsAttribute("cart")) {
            model.addAttribute("cart", new ArrayList<CartItem>());
        }
        if (!model.containsAttribute("totalSum")) {
            model.addAttribute("totalSum", 0.0);
        }
        if (!model.containsAttribute("cartSize")) {
            model.addAttribute("cartSize", 0);
        }
        // Sets the active page to "cart" for highlighting the navbar-link
        model.addAttribute("activePage", "cart");
        return "cart.html";
    }


    @PostMapping("add-to-cart")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam Integer quantity, Model model) {
        Product product = productService.findProductById(productId);
        List<CartItem> cart = (List<CartItem>) model.getAttribute("cart");

        if (product != null) {

            // Checks if product is already in cart. If so adds 1 to quantity
            boolean found = false;
            for (CartItem item : cart) {
                if (item.getProduct().getId().equals(productId)) {
                    item.setQuantity(item.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }

            // If product is not found in cart, adds a new item
            if (!found) {
                CartItem item = new CartItem();
                item.setProduct(product);
                item.setQuantity(quantity);
                cart.add(item);
            };

            // Updates cart in session
            model.addAttribute("cart", cart);
        }

        double totalSum = getTotalSum(cart);
        int cartSize = getCartSize(cart);

        // Updates cartSize and totalSum in session
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("totalSum", totalSum);

        return "redirect:/shop";
    }

    @PostMapping("remove-from-cart")
    public String removeFromCart(@RequestParam Long productId, Model model) {
        List<CartItem> cart = (List<CartItem>) model.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item -> item.getProduct().getId().equals(productId));
        }
        double totalSum = getTotalSum(cart);
        int cartSize = getCartSize(cart);

        model.addAttribute("cart", cart);
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("totalSum", totalSum);
        return "redirect:/cart";
    }

    // Helper methods
    public double getTotalSum(List<CartItem> cart){
        double totalSum = 0.0;
        for (CartItem cartItem : cart) {
            totalSum += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        formatter.format("%.2f", totalSum);
        return totalSum;
    }

    public int getCartSize(List<CartItem> cart){
        int cartSize = 0;
        for (CartItem cartItem : cart) {
            cartSize += cartItem.getQuantity();
        }
        return cartSize;
    }
}
