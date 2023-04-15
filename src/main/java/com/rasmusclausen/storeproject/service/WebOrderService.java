package com.rasmusclausen.storeproject.service;

import com.rasmusclausen.storeproject.entity.WebOrder;
import com.rasmusclausen.storeproject.repository.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;

    public void saveOrder(WebOrder webOrder) {
        webOrderRepository.save(webOrder);
    }

    // Additional methods for order-related operations can be defined here
}
