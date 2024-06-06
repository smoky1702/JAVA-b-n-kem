package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailDAO dao;

	@Override
	public List<OrderDetail> findAll() {
		return dao.findAll();
	}


}
