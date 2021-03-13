package com.spring.jpa.postgresql.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jpa.postgresql.model.Order;
import com.spring.jpa.postgresql.repository.OrderRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderRepository orderRepository;
	
	@PostMapping("/save")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Order _order = orderRepository.save(new Order(order.getOrderId(),order.getQuantity(),order.getUserId(),order.getFoodId(),timestamp));
			return new ResponseEntity<>(_order, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get")
	public ResponseEntity<List<Order>> getAllOrders() {
		try {
			List<Order> order = new ArrayList<Order>();
			orderRepository.findAll().forEach(order::add);
			if (order.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/order")
	public ResponseEntity<HttpStatus> deleteAllOders() {
		try {
			orderRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
		Optional<Order> order = orderRepository.findById(id);

		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@PutMapping("/order/{id}")
	public ResponseEntity<Order> updateBill(@PathVariable("id") Integer id, @RequestBody Order order) {
		Optional<Order> orderData = orderRepository.findById(id);

		if (orderData.isPresent()) {
			Order _order = orderData.get();
			_order.setQuantity(order.getQuantity());
			_order.setUserId(order.getUserId());
			_order.setFoodId(order.getFoodId());
			return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/order/{id}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") Integer id) {
		try {
			orderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
