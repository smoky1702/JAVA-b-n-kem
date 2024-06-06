package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.dao.AccountDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@Controller
public class HomeController {
	private static final Logger logger = LogManager.getLogger(HomeController.class.getName());
	@Autowired
	ProductDAO pdao;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryDAO categoryDao;

	@Autowired
	AccountDAO accountDao;

	@Autowired
	OrderDetailDAO orderDetailDao;

	@RequestMapping({ "/", "/index" })
	public String home(Model model, @RequestParam("cid") Optional<String> cid) {
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
			logger.info("view home by id");
		} else {
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
			logger.info("view home all");
		}
		logger.info("home index");
		return "index";
	}

	@RequestMapping({ "/admin", "/admin/index" })
	public String admin(Model model, RedirectAttributes redirectAttributes) {
//		long productsCount =  pdao.count();
//		long categoriesCount =  categoryDao.count();
//		long accountsCount =  accountDao.count();
//		
//		redirectAttributes.addAttribute("productsCount", productsCount);
//		model.addAttribute("categoriesCount", categoriesCount);
//		redirectAttributes.addAttribute("accountsCount", accountsCount);
		return "redirect:/admin/index.html";
	}


	@RequestMapping("about")
	public String about() {
		return "about";
	}

	@RequestMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/api/categories/count")
    public ResponseEntity<Long> getCategoryCount() {
        long count = categoryDao.count(); // Sử dụng phương thức count() của Spring Data JPA
        return ResponseEntity.ok(count);
    }

}
