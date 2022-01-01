package com.example.Flapkap.controller;

import java.util.*;

import com.example.Flapkap.model.User;
import com.example.Flapkap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "api/v1/user")
public class UserController {
	@Autowired
	UserService userService;

	@ResponseBody
	@GetMapping()
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@ResponseBody
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);
	}

	@ResponseBody
	@PostMapping
	public void registerNewUser(@RequestBody User user) {
		userService.addNewUser(user);
	}

	@DeleteMapping("/{id}")
	public String deleteNewUser(
			@PathVariable("id") long id
			,@RequestBody String username,
			@RequestBody String password) {
		return userService.deleteUser(username,password,id);
	}
	
	@PutMapping("/reset")
	public String resetDeposit(
			@RequestParam String username,
			@RequestParam String password
			) {
		return userService.resetDeposit(username,password);
	}
	
	@PutMapping("/deposit")	
	public String deposit(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam Long amount
			) {
		return userService.deposit(username, password, amount);
	}
}
 