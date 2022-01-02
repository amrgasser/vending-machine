package com.example.Flapkap.service;

import java.util.List;
import java.util.Optional;

import com.example.Flapkap.model.Product;
import com.example.Flapkap.model.User;
import com.example.Flapkap.repository.ProductRepository;
import com.example.Flapkap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public String addProduct(String username,String password,String productName,Integer cost,Integer amountAvailable) {
		try {

			User user = userRepository.findByUsername(username);
			if(user.getPassword().equals(password) == false) {
				return "incorrect username or password";
			}
			if(user.getRole().equals("seller") == false) return "Product has to be added by a seller";
			Product product = new Product();
			product.setAmountAvailable(amountAvailable);
			product.setCost(cost);
			product.setProductName(productName);
			product.setSeller(user);
			productRepository.save(product);
			return "Saved Successfuly";
		} catch (Exception e) {
			return "Could not add product";
		}
	}

//	public String buyProduct(String username, String password ,Long product_id, int amount) {
//		try {
//
//			User user = userRepository.findByUsername(username);
//			if(user.getPassword().equals(password) == false) {
//				return "incorrect username or password";
//			}
//			Product product = productRepository.findById(product_id).get();
//			Integer amountAvailable =product.getAmountAvailable();
//			if(amount > amountAvailable) {
//				return "Not enough quantity available";
//			}
//			int price =  product.getCost();
//			if(user.getDeposit() < amount * price ) {
//				return "Not enough funds";
//			}
//
//			product.setAmountAvailable(amountAvailable-amount);
//			user.setDeposit(user.getDeposit() - (amount * price));
//
//			userRepository.save(user);
//			productRepository.save(product);
//
//			return "Bought successfuly";
//		} catch (Exception e) {
//			return "Something went wrong";
//		}
//	}

	public String deleteProduct(String username, String password, Long id) {
		User user = userRepository.findByUsername(username);
		if(user.getPassword().equals(password) == false) {
			return "incorrect username or password";
		}
		productRepository.deleteById(id);
		return "Deleted!";
	}

	public String updateProduct(String username, String password, Long id, String productName, Integer cost,
			Integer amountAvailable) {
		try {
			
			User user = userRepository.findByUsername(username);
			
//			User user = new User();
			if(user.getPassword().equals(password) == false) {
				return "incorrect username or password";
			}
			
			Product product = productRepository.findById(id).get();
			System.out.print(product.getSeller());
			
			User seller = product.getSeller();
			
			System.out.print(seller.getUsername());
			
			if(seller.getUsername().equals(user.getUsername()) == false) return "Not permitted to update Product";
			if(productName != null) product.setProductName(productName); 
			if(cost != null) product.setCost(cost); 
			if(amountAvailable != null) product.setAmountAvailable(amountAvailable); 
			
			productRepository.save(product);
			return "Product Updated Successfuly";
			
		} catch (Exception e) {
			return "Something went wrong";
		}
	}

	public Product getProduct(Long id) {
		Optional<Product> product = null;
		try {
			product = productRepository.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return product.get();
	}

}
