package com.example.Flapkap.controller;

import java.util.List;

import com.example.Flapkap.model.Product;
import com.example.Flapkap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="api/v1/product")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@ResponseBody
	@GetMapping
	public List<Product> getProducts(){
		return productService.getProducts();
	}

	@ResponseBody
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id){
		return productService.getProduct(id);
	}

	@ResponseBody
	@PostMapping
	public String addProduct(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String productName,
			@RequestParam Integer cost,
			@RequestParam Integer amountAvailable
			) {
		return productService.addProduct(username,password,productName,cost,amountAvailable);
	}

	@ResponseBody
	@DeleteMapping("/{id}")
	public String deleteProduct(
			@PathVariable("id") Long id,
			@RequestParam String username,
			@RequestParam String password
			) {
		return productService.deleteProduct(username,password,id);
	}
//
//	@ResponseBody
//	@PutMapping("/buy/{id}")
//	public String buyProduct(
//			@RequestParam String username,
//			@RequestParam String password,
//			@PathVariable("id") Long product_id,
//			@RequestParam Integer amount
//			) {
//		return productService.buyProduct(username,password,product_id, amount);
//	}

	@ResponseBody
	@PutMapping("/{id}")
	public String updateProduct(
			@RequestParam String username,
			@RequestParam String password,
			@PathVariable("id") Long id,
			@RequestParam String productName,
			@RequestParam Integer cost,
			@RequestParam Integer amountAvailable
			) {

		return productService.updateProduct(username, password, id, productName, cost, amountAvailable);
	}
}
