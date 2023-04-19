package com.rasmusclausen.shopproject.controller;

import com.rasmusclausen.shopproject.entity.CartItem;
import com.rasmusclausen.shopproject.entity.Product;
import com.rasmusclausen.shopproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

        // Ensures that relevant session attributes are added
        if (!model.containsAttribute("cart")) {
            model.addAttribute("cart", new ArrayList<CartItem>());
        }
        if (!model.containsAttribute("totalSum")) {
            model.addAttribute("totalSum", 0.0);
        }
        if (!model.containsAttribute("cartSize")) {
            model.addAttribute("cartSize", 0);
        }

        // Gets cart from session
        List<CartItem> cart = (List<CartItem>) model.getAttribute("cart");

        // This if block contains the code for determining what other products
        // the user might be interested in. It is based upon the genres and platforms
        // of the products in cart and the genres and platform of the products available
        if (!cart.isEmpty()) {

            List<Product> allProducts = productService.getAllProducts();
            // Initializes the products in cart
            List<Product> productsInCart = new ArrayList<>();
            // Initializes the platforms of the products in cart
            Set<String> platformsInCart = new HashSet<>();
            // Initializes the interested in products
            List<Product> interestedInProducts = new ArrayList<>();
            // Initializes the interested in product genres
            Set<String> genresInterest = new HashSet<>();

            // Adds products and platforms from cart to the new ArrayList and HashSet
            for (CartItem cartItem : cart) {
                productsInCart.add(cartItem.getProduct());
                platformsInCart.add(cartItem.getProduct().getPlatform());
            }

            // Adds all genres of the products in cart to the genresInterest set
            for (Product product : productsInCart) {
                genresInterest.addAll(product.getGenres());
            }

            // Filters all products to include only those with platforms that match the
            // platforms of the products in cart
            List<Product> productsWithMatchingPlatforms = allProducts.stream()
                    .filter(product -> platformsInCart.contains(product.getPlatform())).toList();

            // Filters the products with matching platforms to include only those that have
            // at least one genre in common with the products in cart
            interestedInProducts = productsWithMatchingPlatforms.stream()
                    .filter(product -> {
                        for (String genre : product.getGenres()) {
                            if (genresInterest.contains(genre) && !isProductInCart(product, cart)) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

            // Randomly selects up to two products to display
            Collections.shuffle(interestedInProducts);
            interestedInProducts = interestedInProducts.stream().limit(2).collect(Collectors.toList());

            // Adds the interested products to the model
            model.addAttribute("interestedInProducts", interestedInProducts);
        }

        // Sets the active page to "cart" for highlighting the navbar-link
        model.addAttribute("activePage", "cart");

        return "cart.html";
    }

    @PostMapping("add-to-cart")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam Integer quantity,
                            Model model) {
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

    @PostMapping("changeQuantity")
    public String changeQuantity(@RequestParam Long productId,
                                 @RequestParam Integer quantity,
                                 @RequestParam(required = false) Boolean incQuantity,
                                 @RequestParam(required = false) Boolean decQuantity,
                                 Model model) {
        Product product = productService.findProductById(productId);
        // Gets cart from session
        List<CartItem> cart = (List<CartItem>) model.getAttribute("cart");

            if (incQuantity != null && incQuantity && quantity < 10) {
                {
                    for (CartItem cartItem : cart) {
                        if (cartItem.getProduct().getId().equals(product.getId())) {
                            cartItem.setQuantity(cartItem.getQuantity() + 1);
                        }
                    }
                }
            }

            if (decQuantity != null && decQuantity && quantity > 1) {
                for (CartItem cartItem : cart) {
                    if (cartItem.getProduct().getId().equals(product.getId())) {
                        cartItem.setQuantity(cartItem.getQuantity() - 1);
                    }
                }
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

    private boolean isProductInCart(Product product, List<CartItem> cart) {
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }
}
