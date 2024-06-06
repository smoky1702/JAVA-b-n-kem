//package com.poly.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import java.time.LocalDateTime;
//
//import com.poly.dao.AccountDAO;
//import com.poly.dao.CategoryDAO;
//import com.poly.dao.OrderDetailDAO;
//import com.poly.dao.ProductDAO;
//
//@Controller
//public class DashboardController {
//	@Autowired
//	ProductDAO productDao;
//
//	@Autowired
//	CategoryDAO categoryDao;
//
//	@Autowired
//	AccountDAO accountDao;
//
//	@Autowired
//	OrderDetailDAO orderDetailDao;
//
//	@GetMapping("/admin/index.html")
//	public String index(Model model) {
//	    // Lấy dữ liệu thống kê
//	    int productsCount = (int) productDao.count();
//	    int categoriesCount = (int) categoryDao.count();
//	    int accountsCount = (int) accountDao.count();
//
//	    // Lấy tổng tất cả price đã thực hiện gần đây của OrderDetail
////	    double totalPrice = orderDetailDao.findSumByCreatedAtBetween(LocalDateTime.now().minusDays(7), LocalDateTime.now()).orElse(0);
//
//	    // Gửi dữ liệu đến view
//	    model.addAttribute("productsCount", productsCount);
//	    model.addAttribute("categoriesCount", categoriesCount);
//	    model.addAttribute("accountsCount", accountsCount);
////	    model.addAttribute("totalPrice", totalPrice);
//
//	    return "/admin/index";
//	}
//}
