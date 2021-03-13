package com.spring.jpa.postgresql.controller;

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

import com.spring.jpa.postgresql.model.Bill;
import com.spring.jpa.postgresql.repository.BillRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/bills")
public class BillController {
	@Autowired
	BillRepository billRepository;

	@PostMapping("/save")
	public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
		try {
			Bill _bill = billRepository
					.save(new Bill(bill.getBillId(), bill.getUserId(),bill.getTotalAmount(),bill.getOrderId(),bill.getPaymentMode(),bill.getPaymentStatus()));
			return new ResponseEntity<>(_bill, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get")
	public ResponseEntity<List<Bill>> getAllBills() {
		try {
			List<Bill> menu = new ArrayList<Bill>();
			billRepository.findAll().forEach(menu::add);
			if (menu.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(menu, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/bill")
	public ResponseEntity<HttpStatus> deleteAllBills() {
		try {
			billRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/bill/{id}")
	public ResponseEntity<Bill> getBillById(@PathVariable("id") Integer id) {
		Optional<Bill> bill = billRepository.findById(id);

		if (bill.isPresent()) {
			return new ResponseEntity<>(bill.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@PutMapping("/bill/{id}")
	public ResponseEntity<Bill> updateBill(@PathVariable("id") Integer id, @RequestBody Bill bill) {
		Optional<Bill> billData = billRepository.findById(id);

		if (billData.isPresent()) {
			Bill _bill = billData.get();
			_bill.setBillId(bill.getBillId());
			_bill.setUserId(bill.getUserId());
			_bill.setTotalAmount(bill.getTotalAmount());
			_bill.setOrderId(bill.getOrderId());
			_bill.setPaymentMode(bill.getPaymentMode());
			_bill.setPaymentStatus(bill.getPaymentStatus());
			return new ResponseEntity<>(billRepository.save(_bill), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/bill/{id}")
	public ResponseEntity<HttpStatus> deleteBill(@PathVariable("id") Integer id) {
		try {
			billRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
