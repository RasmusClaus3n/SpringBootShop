package com.rasmusclausen.storeproject.controller;

import com.rasmusclausen.storeproject.entity.CartItem;
import com.rasmusclausen.storeproject.entity.Customer;
import com.rasmusclausen.storeproject.entity.WebOrder;
import com.rasmusclausen.storeproject.service.WebOrderService;
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
        webOrder.setTotalSum(getTotalSum(cart));
        webOrder.setCustomer(customer);

        // Saves cart items from cart to web order
        for (CartItem cartItem: cart){
            webOrder.addCartItem(cartItem);
        }

        // Saves web order to database
        webOrderService.saveWebOrder(webOrder);

        // Clears the cart
        cart.clear();

        // Update model attributes
        model.addAttribute("cart", cart);
        model.addAttribute("cartSize", 0);
        model.addAttribute("totalSum", 0.0);

        // No errors:
        List<WebOrder> webOrders = webOrderService.getAllWebOrders();
        for (WebOrder order : webOrders) {
            System.out.println("After processWebOrder(): " + order.getCartItems().size());
        }

        return "redirect:/web-order/all";
    }

    @Transactional
    @GetMapping("all")
    public String getAllWebOrders(Model model){

        //FIXME duplicate of CartItem in WebOrder
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
