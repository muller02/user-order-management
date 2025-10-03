package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @PostMapping(value="/save")
    public String saveProduct(@RequestBody ProductDTO productDTO) {
        
        return null;
    }
    
    @GetMapping(value="/get")
    public String getMethodName(@RequestParam String productId) {
        return null;
    }
    
}
