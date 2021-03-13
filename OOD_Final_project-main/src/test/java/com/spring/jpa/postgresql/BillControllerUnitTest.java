package com.spring.jpa.postgresql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.jpa.postgresql.controller.BillController;
import com.spring.jpa.postgresql.model.Bill;
import com.spring.jpa.postgresql.repository.BillRepository;

@ExtendWith(MockitoExtension.class)
public class BillControllerUnitTest {

	@InjectMocks
	BillController billController;

	@Mock
	BillRepository billRepository;

	@Test
	public void testAddBill() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Bill bill = new Bill();
		bill.setBillId(1);
		when(billRepository.save(any(Bill.class))).thenReturn(bill);

		Bill employeeToAdd = new Bill(1, "230", "1", "CASH", "DONE");
		ResponseEntity<Bill> responseEntity = billController.createBill(employeeToAdd);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}

	@Test
	public void testFindAll() {
		// given
		Bill bill1 = new Bill(1, "230", "1", "CASH", "DONE");
		Bill bill2 = new Bill(1, "750", "1", "CARD", "PENDING");
		List<Bill> list = new ArrayList<Bill>();
		list.addAll(Arrays.asList(bill1, bill2));

		when(billRepository.findAll()).thenReturn(list);

		// when
		ResponseEntity<List<Bill>> result = billController.getAllBills();
		
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(200);
		
		assertThat(result.getBody().size()).isEqualTo(2);

		assertThat(result.getBody().get(0).getOrderId()).isEqualTo(bill1.getOrderId());

		assertThat(result.getBody().get(1).getOrderId()).isEqualTo(bill2.getOrderId());
	}
}
