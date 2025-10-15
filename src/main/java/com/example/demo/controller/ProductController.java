package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    /*
        RequiredArgsConstructor + final 조합으로 주입 시 생성자 생략
        Spring 4.3 이상부터는 @Autowired 생략 가능
    */ 
    private final ProductService productService;

    @GetMapping("/all")
        public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getProductStock()))
                .toList();
    }

        @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long productId) {
        
        //TODO: Not Found Exception 처리 필요
        Product product = productService.getProductById(productId);

        return new ProductDTO(product.getProductId(), 
                                product.getProductName(),
                                product.getProductPrice(), 
                                product.getProductStock());
    }

    //TODO: Response객체로 return 필요
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {

        // DTO -> Entity
        Product product = Product.builder()
            .productName(productDTO.productName())
            .productPrice(productDTO.productPrice())
            .productStock(productDTO.productStock())
            .build();

        // 저장된 Product Entity 반환
        // TODO: 저장 성공, 실패 Exception 처리 필요 (Global Exception Handler)
        Product saved = productService.createProduct(product);

        return new ProductDTO(saved.getProductId(), 
                                saved.getProductName(),
                                saved.getProductPrice(), 
                                saved.getProductStock());
    }
}