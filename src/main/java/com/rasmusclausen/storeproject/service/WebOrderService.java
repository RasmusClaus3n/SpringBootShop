package com.rasmusclausen.storeproject.service;

import com.rasmusclausen.storeproject.entity.WebOrder;
import com.rasmusclausen.storeproject.repository.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;

    public void saveWebOrder(WebOrder webOrder) {
        webOrderRepository.save(webOrder);
    }

    public List<WebOrder> getAllWebOrders(){
        return webOrderRepository.findAll();
    }
}
