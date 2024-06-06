package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;

public interface OrderDetailService {
	List<OrderDetail> findAll();

	
}
