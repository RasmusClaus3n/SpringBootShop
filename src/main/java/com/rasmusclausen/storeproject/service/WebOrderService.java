package com.rasmusclausen.storeproject.service;

import com.rasmusclausen.storeproject.entity.WebOrder;
import com.rasmusclausen.storeproject.repository.WebOrderRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;
    @Autowired
    EntityManager entityManager;

    public void saveWebOrder(WebOrder webOrder) {
        webOrderRepository.save(webOrder);
    }

    // A very funky method
    public List<WebOrder> getAllWebOrders() {
        return entityManager.createQuery("SELECT DISTINCT w FROM WebOrder w LEFT JOIN FETCH w.cartItems", WebOrder.class)
                .getResultList();
    }
}
