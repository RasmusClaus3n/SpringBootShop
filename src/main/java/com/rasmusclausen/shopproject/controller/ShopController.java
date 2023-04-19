package com.rasmusclausen.shopproject.controller;

import com.rasmusclausen.shopproject.entity.CartItem;
import com.rasmusclausen.shopproject.entity.Product;
import com.rasmusclausen.shopproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"cart", "totalSum", "cartSize"})
@RequestMapping("shop")
public class ShopController {

    @Autowired
    ProductService productService;

    // Number of products to display per page
    private static final int pageSize = 8;

    @GetMapping("")
    public String getAllProducts(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(value = "platform", required = false) String platform,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "sortBy", required = false) String sortBy,
                                 Model model) {

        if (!model.containsAttribute("cart")) {
            model.addAttribute("cart", new ArrayList<CartItem>());
        }

        Pageable pageable;

        // Sort products based on price
        if ("lowest".equalsIgnoreCase(sortBy)) {
            pageable = PageRequest.of(page, pageSize, Sort.by("price").ascending());
        } else if ("highest".equalsIgnoreCase(sortBy)) {
            pageable = PageRequest.of(page, pageSize, Sort.by("price").descending());
        } else {
            pageable = PageRequest.of(page, pageSize);
        }

        // Sort products based on platform and name
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

        // Sets the list of all products and sets total pages to display
        List<Product> allProducts = productsPage.getContent();
        int totalPages = productsPage.getTotalPages();

        // Gets count of how many products by platform. Used for shop sidebar
        long ps4Count = productService.getProductCountByPlatform("ps4");
        long ps5Count = productService.getProductCountByPlatform("ps5");
        long switchCount = productService.getProductCountByPlatform("switch");
        long xboxCount = productService.getProductCountByPlatform("xboxOne");

        // Sets the active page to "shop" for highlighting the navbar-link
        model.addAttribute("activePage", "shop");

        // Updates model attributes
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

    @GetMapping("/product/{productId}")
    public String getProductById(@PathVariable Long productId, Model model) {
        // Gets product by id
        Product product = productService.findProductById(productId);
        model.addAttribute("product", product);
        return "product";
    }
}
