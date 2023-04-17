package com.rasmusclausen.storeproject.service;

import com.rasmusclausen.storeproject.entity.CartItem;
import com.rasmusclausen.storeproject.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }
}
