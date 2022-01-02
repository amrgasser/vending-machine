package com.example.Flapkap.controller;

import com.example.Flapkap.service.OperationService;
import com.example.Flapkap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "api/v1/operations")
public class OperationController {
    @Autowired
    OperationService operationService;

    @PutMapping("/reset")
    public String resetDeposit(
            @RequestParam String username,
            @RequestParam String password
    ) {
        return operationService.resetDeposit(username,password);
    }

    @PutMapping("/deposit")
    public String deposit(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Long amount
    ) {
        return operationService.deposit(username, password, amount);
    }


    @ResponseBody
    @PutMapping("/buy/{id}")
    public String buyProduct(
            @RequestParam String username,
            @RequestParam String password,
            @PathVariable("id") Long product_id,
            @RequestParam Integer amount
    ) {
        return operationService.buyProduct(username,password,product_id, amount);
    }
}
