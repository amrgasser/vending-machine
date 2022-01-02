package com.example.Flapkap.service;

import com.example.Flapkap.model.Product;
import com.example.Flapkap.model.User;
import com.example.Flapkap.repository.ProductRepository;
import com.example.Flapkap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public String buyProduct(String username, String password ,Long product_id, int amount) {
        try {

            User user = userRepository.findByUsername(username);
            if(user.getPassword().equals(password) == false) {
                return "incorrect username or password";
            }
            Product product = productRepository.findById(product_id).get();
            Integer amountAvailable =product.getAmountAvailable();
            if(amount > amountAvailable) {
                return "Not enough quantity available";
            }
            int price =  product.getCost();
            if(user.getDeposit() < amount * price ) {
                return "Not enough funds";
            }

            product.setAmountAvailable(amountAvailable-amount);
            user.setDeposit(user.getDeposit() - (amount * price));

            userRepository.save(user);
            productRepository.save(product);

            return "Bought successfuly";
        } catch (Exception e) {
            return "Something went wrong";
        }
    }

    public String resetDeposit(String username, String password) {
        try {
            User user = userRepository.findByUsername(username);
            System.out.println(password);
            System.out.println(user.getPassword());
            if(user.getPassword().equals(password) == false) {
                return "incorrect username or password";
            }
            user.setDeposit((long) 0);
            return "Depost reset";
        } catch (Exception e) {
            return "Error";
        }
    }

    public String deposit ( String username, String password, Long amount ) {
        try {
            User user = userRepository.findByUsername(username);
            if (user.getPassword().equals(password) && user.getRole().equals("buyer")) {
                if ( amount == 5 || amount == 10 || amount == 20 || amount == 50 || amount == 100 ) {
                    user.setDeposit(user.getDeposit()+amount);
                    userRepository.save(user);
                    return "Successfully Charged";
                }
                else {
                    return "Amount is Incorrect, Only the following are accepted ( 5, 10, 20, 50, 100 )";
                }
            }
            else {
                return "Authentication Failed";
            }
        }
        catch (Exception e) {
            return "Something went wrong";
        }
    }
}
