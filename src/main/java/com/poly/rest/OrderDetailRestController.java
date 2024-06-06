package com.poly.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderdetails")
public class OrderDetailRestController {

	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping
	public List<OrderDetail> getAll() {
		return orderDetailService.findAll();
	}
}
