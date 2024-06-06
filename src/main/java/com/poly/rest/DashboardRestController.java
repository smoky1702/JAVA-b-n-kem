package com.poly.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.dao.ProductDAO;
import com.poly.entity.OrderDetail;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/dashboard")
public class DashboardRestController {

    @Autowired
    ProductDAO productDao;

    @Autowired
    CategoryDAO categoryDao;
    
    @Autowired
    AccountDAO accountDao;
    
    @Autowired
    OrderDetailDAO orderDetailDao;
    
    @GetMapping("/productCount")
    public Map<String, Object> getProductInfo() {
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("productCount", productDao.count());
        // Thêm các thông tin khác nếu cần
        return productInfo;
    }
    
    @GetMapping("/categoryCount")
    public Map<String, Object> getCategoryInfo() {
        Map<String, Object> categoryInfo = new HashMap<>();
        categoryInfo.put("categoryCount", categoryDao.count());
        // Thêm các thông tin khác nếu cần
        return categoryInfo;
    }
    
    @GetMapping("/accountCount")
    public Map<String, Object> getAccountInfo() {
        Map<String, Object> accountInfo = new HashMap<>();
        accountInfo.put("accountCount", accountDao.count());
        // Thêm các thông tin khác nếu cần
        return accountInfo;
    }
    
    @GetMapping("/totalCount")
    public Map<String, Object> getTotalInfo() {
        Map<String, Object> totalInfo = new HashMap<>();
        totalInfo.put("totalCount", orderDetailDao.getTotalRevenue());
        return totalInfo;
    }
}

