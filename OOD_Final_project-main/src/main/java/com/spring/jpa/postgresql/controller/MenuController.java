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

import com.spring.jpa.postgresql.model.Menu;
import com.spring.jpa.postgresql.repository.MenuRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/menus")
public class MenuController {

	@Autowired
	MenuRepository menuRepository;

	@PostMapping("/save")
	public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
		try {
			Menu _menu = menuRepository.save(new Menu(menu.getFoodItem(), menu.getPrice()));
			return new ResponseEntity<>(_menu, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get")
	public ResponseEntity<List<Menu>> getAllMenu() {
		try {
			List<Menu> menu = new ArrayList<Menu>();
			menuRepository.findAll().forEach(menu::add);
			if (menu.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(menu, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/menu")
	public ResponseEntity<HttpStatus> deleteAllMenu() {
		try {
			menuRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping("/menu/{id}")
	public ResponseEntity<Menu> getMenuById(@PathVariable("id") Integer id) {
		Optional<Menu> bill = menuRepository.findById(id);

		if (bill.isPresent()) {
			return new ResponseEntity<>(bill.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@PutMapping("/menu/{id}")
	public ResponseEntity<Menu> updateMenu(@PathVariable("id") Integer id, @RequestBody Menu menu) {
		Optional<Menu> menuData = menuRepository.findById(id);

		if (menuData.isPresent()) {
			Menu _menu = menuData.get();
			_menu.setFoodId(menu.getFoodId());
			_menu.setFoodItem(menu.getFoodItem());
			_menu.setPrice(menu.getPrice());
			return new ResponseEntity<>(menuRepository.save(_menu), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
