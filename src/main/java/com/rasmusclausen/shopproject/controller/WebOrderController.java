package com.rasmusclausen.shopproject.controller;

import com.rasmusclausen.shopproject.entity.CartItem;
import com.rasmusclausen.shopproject.entity.Customer;
import com.rasmusclausen.shopproject.entity.WebOrder;
import com.rasmusclausen.shopproject.service.WebOrderService;
import jakarta.transaction.Transactional;
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

    @Transactional
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

        // Saves cart items from cart to web order
        for (CartItem cartItem: cart){
            webOrder.addCartItem(cartItem);
        }

        // Sets total sum and customer to web order
        webOrder.setTotalSum(getTotalSum(cart));
        webOrder.setCustomer(customer);

        // Saves web order to database
        webOrderService.saveWebOrder(webOrder);

        // Clears the cart
        cart.clear();

        // Updates model attributes
        model.addAttribute("cart", cart);
        model.addAttribute("cartSize", 0);
        model.addAttribute("totalSum", 0.0);

        return "redirect:/web-order/all";
    }

    @Transactional
    @GetMapping("all")
    public String getAllWebOrders(Model model){
        model.addAttribute("activePage", "web-orders");
        List <WebOrder> webOrders = webOrderService.getAllWebOrders();
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
