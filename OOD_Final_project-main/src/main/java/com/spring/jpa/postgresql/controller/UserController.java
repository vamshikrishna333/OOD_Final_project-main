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

import com.spring.jpa.postgresql.model.User;
import com.spring.jpa.postgresql.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/save")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User _user = userRepository.save(new User( user.getUserId(),user.getfName(), user.getlName(), user.getMobileNumber(), user.getEmail(),user.getCreatedOn(),user.getUserType()));
			//User _user = userRepository.save(user);
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> user = new ArrayList<User>();
			userRepository.findAll().forEach(user::add);
			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getOrderById(@PathVariable("id") Integer id) {
		Optional<User> order = userRepository.findById(id);

		if (order.isPresent()) {
			return new ResponseEntity<>(order.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setUserId(user.getUserId());
			_user.setfName(user.getfName()); 
			_user.setlName(user.getlName());
			_user.setMobileNumber(user.getMobileNumber());
			_user.setEmail(user.getEmail());
			_user.setCreatedOn(user.getCreatedOn());
			_user.setUserType(user.getUserType());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

