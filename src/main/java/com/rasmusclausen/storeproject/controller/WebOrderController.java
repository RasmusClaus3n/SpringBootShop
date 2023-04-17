package com.rasmusclausen.storeproject.controller;

import com.rasmusclausen.storeproject.entity.CartItem;
import com.rasmusclausen.storeproject.entity.Customer;
import com.rasmusclausen.storeproject.entity.WebOrder;
import com.rasmusclausen.storeproject.service.CartItemService;
import com.rasmusclausen.storeproject.service.WebOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Formatter;
import java.util.List;

@Controller
@SessionAttributes({"cart", "totalSum", "cartSize"})
@RequestMapping("web-order")
public class WebOrderController {

    // Used to format totalSum
    private static final Formatter formatter = new Formatter();

    @Autowired
    WebOrderService webOrderService;
    @Autowired
    CartItemService cartItemService;

    @PostMapping("add")
    public String processWebOrder(@Valid @RequestParam(value = "firstName") String firstName,
                                  @Valid @RequestParam(value = "lastName") String lastName,
                                  @Valid @RequestParam(value = "address") String address,
                                  Model model) {
        // Gets the cart from session
        List<CartItem> cart = (List<CartItem>) model.getAttribute("cart");

        // Creates new customer based on valid request params
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);

        // Creates new web order
        WebOrder webOrder = new WebOrder();
        webOrder.setTotalSum(getTotalSum(cart));
        webOrder.setCustomer(customer);

        for(CartItem cartItem: cart){
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(cartItem.getProduct());
            newCartItem.setQuantity(cartItem.getQuantity());
            webOrder.addCartItem(newCartItem);
        };

        // Sets web order to customer
        customer.setWebOrder(webOrder);

        // Saves web order to database
        webOrderService.saveWebOrder(webOrder);

        List<WebOrder> webOrders = webOrderService.getAllWebOrders();
        for (WebOrder order : webOrders) {
            System.out.println("Before cart.clear(): " + order.getCartItems().size());
        }

        // Clears the cart
        cart.clear();

        for (WebOrder order : webOrders) {
            System.out.println("After cart.clear(): " + order.getCartItems().size());
        }

        // Update model attributes
        model.addAttribute("cart", cart);
        model.addAttribute("cartSize", 0);
        model.addAttribute("totalSum", 0.0);

        return "redirect:/web-order/all";
    }

    @GetMapping("all")
    public String getAllWebOrders(Model model){
        List <WebOrder> webOrders = webOrderService.getAllWebOrders();

        for(WebOrder order : webOrders){
            System.out.println("After getAllWebOrders(): " + order.getCartItems().size());
        }

        model.addAttribute("webOrders", webOrders);
        return "web-orders";
    }

    public double getTotalSum(List<CartItem> cart){

        double totalSum = 0.0;
        for (CartItem cartItem : cart) {
            totalSum += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        formatter.format("%.2f", totalSum);
        return totalSum;
    }
}
