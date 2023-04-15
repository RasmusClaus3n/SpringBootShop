package com.rasmusclausen.storeproject.controller;

import com.rasmusclausen.storeproject.entity.CartItem;
import com.rasmusclausen.storeproject.entity.Customer;
import com.rasmusclausen.storeproject.entity.WebOrder;
import com.rasmusclausen.storeproject.service.WebOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Formatter;
import java.util.List;

@Controller
@SessionAttributes({"cart", "totalSum", "cartSize"})
@RequestMapping("web-order")
public class WebOrderController {

    private static final Formatter formatter = new Formatter();

    @Autowired
    WebOrderService webOrderService;

    @PostMapping("add")
    public String processWebOrder(@RequestParam(value = "firstName") String firstName,
                                  @RequestParam(value = "lastName") String lastName,
                                  @RequestParam(value = "address") String address,
                                  Model model){
        List<CartItem> cart = (List<CartItem>) model.getAttribute("cart");

        WebOrder webOrder = new WebOrder();

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);

        webOrder.setCartItems(cart);
        webOrder.setTotalSum(getTotalSum(cart));
        webOrder.setCustomer(customer);
        customer.setWebOrder(webOrder);
        webOrderService.saveOrder(webOrder);

        cart.clear();

        model.addAttribute("cart", cart);
        model.addAttribute("cartSize", 0);
        model.addAttribute("totalSum", 0.0);

        return "redirect:/cart";
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
