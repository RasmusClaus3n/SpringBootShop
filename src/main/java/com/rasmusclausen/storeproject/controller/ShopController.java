package com.rasmusclausen.storeproject.controller;

import com.rasmusclausen.storeproject.entity.CartItem;
import com.rasmusclausen.storeproject.entity.Product;
import com.rasmusclausen.storeproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

@Controller
@SessionAttributes({"cart", "totalSum", "cartSize"})
@RequestMapping("shop")
public class ShopController {

    @Autowired
    ProductService productService;

    private static final Formatter formatter = new Formatter();

    @GetMapping("")
    public String getAllProducts(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(value = "platform", required = false) String platform,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "sortBy", required = false) String sortBy,
                                 Model model) {

        if (!model.containsAttribute("cart")) {
            model.addAttribute("cart", new ArrayList<CartItem>());
        }

        int pageSize = 8; // Number of products to display per page
        Pageable pageable;

        // Sort products based on sortBy parameter
        if ("lowest".equalsIgnoreCase(sortBy)) {
            pageable = PageRequest.of(page, pageSize, Sort.by("price").ascending());
        } else if ("highest".equalsIgnoreCase(sortBy)) {
            pageable = PageRequest.of(page, pageSize, Sort.by("price").descending());
        } else {
            pageable = PageRequest.of(page, pageSize);
        }

        Page<Product> productsPage;
        if (platform != null && name != null) {
            productsPage = productService.findByPlatformAndName(platform, name, pageable);
        } else if (platform != null) {
            productsPage = productService.findByPlatform(platform, pageable);
        } else if (name != null) {
            productsPage = productService.findByName(name, pageable);
        } else {
            productsPage = productService.findAllProducts(pageable);
        }

        List<Product> allProducts = productsPage.getContent();
        int totalPages = productsPage.getTotalPages();

        long ps4Count = productService.getProductCountByPlatform("ps4");
        long ps5Count = productService.getProductCountByPlatform("ps5");
        long switchCount = productService.getProductCountByPlatform("switch");
        long xboxCount = productService.getProductCountByPlatform("xboxOne");

        // Sets the active page to "shop". Only for highlighting the navbar-link
        model.addAttribute("activePage", "shop");

        model.addAttribute("ps4Count", ps4Count);
        model.addAttribute("ps5Count", ps5Count);
        model.addAttribute("switchCount", switchCount);
        model.addAttribute("xboxCount", xboxCount);

        model.addAttribute("allProducts", allProducts);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("platform", platform);
        model.addAttribute("name", name);
        model.addAttribute("sortBy", sortBy);

        return "shop.html";
    }
}
