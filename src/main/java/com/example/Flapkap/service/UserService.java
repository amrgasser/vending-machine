package com.example.Flapkap.service;

import java.util.*;

import com.example.Flapkap.model.User;
import com.example.Flapkap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}

	public void addNewUser(User user) {

		User newUser = new User();
		newUser.setDeposit(user.getDeposit());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		newUser.setRole(user.getRole());
		try{
			userRepository.save(newUser);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}

	public String deleteUser(String username, String password, long id) {
		try {
			User user = userRepository.findByUsername(username);
			if(user.getPassword().equals(password) == false) return "incorrect username or password";
			else if(user.getRole().equals("admin") == false) return "Not authorized";
			else {
			userRepository.deleteById(id);
			return "User deleted";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "Error";
		}
	}


	public String updateUser(long id, User user) {
		try {
			User userCheck = userRepository.findByUsername(user.getUsername());
			if(user.getPassword().equals(userCheck.getPassword()) == false)
				return "password is not correct";

			return "User updated";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}

//	public String resetDeposit(String username, String password) {
//		try {
//			User user = userRepository.findByUsername(username);
//			System.out.println(password);
//			System.out.println(user.getPassword());
//			if(user.getPassword().equals(password) == false) {
//				return "incorrect username or password";
//			}
//			user.setDeposit((long) 0);
//			return "Depost reset";
//		} catch (Exception e) {
//			return "Error";
//		}
//	}
//
//	public String deposit ( String username, String password, Long amount ) {
//        try {
//            User user = userRepository.findByUsername(username);
//            if (user.getPassword().equals(password) && user.getRole().equals("buyer")) {
//                if ( amount == 5 || amount == 10 || amount == 20 || amount == 50 || amount == 100 ) {
//                    user.setDeposit(user.getDeposit()+amount);
//                    userRepository.save(user);
//                    return "Successfully Charged";
//                }
//                else {
//                    return "Amount is Incorrect, Only the following are accepted ( 5, 10, 20, 50, 100 )";
//                }
//            }
//            else {
//                return "Authentication Failed";
//            }
//        }
//        catch (Exception e) {
//            return "Something went wrong";
//        }
//    }

	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
	
}
