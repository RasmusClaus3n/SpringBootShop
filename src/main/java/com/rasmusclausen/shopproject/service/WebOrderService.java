package com.rasmusclausen.shopproject.service;

import com.rasmusclausen.shopproject.entity.WebOrder;
import com.rasmusclausen.shopproject.exception.WebOrderNotFoundException;
import com.rasmusclausen.shopproject.repository.WebOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;
    @Autowired
    EntityManager entityManager;

    @Transactional
    public void saveWebOrder(WebOrder webOrder) {
        webOrderRepository.save(webOrder);
    }

    // For some reason the standard built in JPA-repository method findAll() did not represent the database correctly.
    // There was an issue where a duplicate entry of CartItem occurred in the hibernate session but not in the database.
    // The custom query bellow solved the issue

    public List<WebOrder> getAllWebOrders() {
        return entityManager.createQuery("SELECT DISTINCT w FROM WebOrder w LEFT JOIN FETCH w.cartItems", WebOrder.class)
                .getResultList();
    }

    @Transactional
    public void updateWebOrder(WebOrder webOrder) throws WebOrderNotFoundException {
        WebOrder existingWebOrder = webOrderRepository.findById(webOrder.getId())
                .orElseThrow(() -> new WebOrderNotFoundException("Web order with id " + webOrder.getId() + " was not found"));

        // Update the existing web order with the new values
        existingWebOrder.setTotalSum(webOrder.getTotalSum());
        existingWebOrder.setCustomer(webOrder.getCustomer());
        existingWebOrder.setCartItems(webOrder.getCartItems());

        // Save the updated web order
        webOrderRepository.save(existingWebOrder);
    }
}
