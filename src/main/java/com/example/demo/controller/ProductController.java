package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductPatchDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    /*
        RequiredArgsConstructor + final 조합으로 주입 시 생성자 생략
        Spring 4.3 이상부터는 @Autowired 생략 가능
    */ 
    private final ProductService productService;

    // 전체 조회
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

    // 단건 조회
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long productId) {
        ProductDTO productDTO = productService.getProductById(productId);
        return productDTO;
    }

    //TODO: Response객체로 return 필요
    // 등록
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

    // 전체 수정
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.updateProduct(productId, productDTO);
        return updated;
    }

    // 단건 삭제
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
    }

    // 부분 수정
    @PatchMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long productId, @RequestBody ProductPatchDTO patchDTO) {
        ProductDTO updated = productService.updateProduct(productId, patchDTO);
        return updated;
    }
}