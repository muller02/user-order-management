package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.dto.ProductCreateDTO;
import com.example.demo.dto.ProductPatchDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
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
    @Transactional(readOnly = true)
    @GetMapping("/all")
        public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if(products.isEmpty())
            return null;

        log.info("products => "+products.toString());

        List<ProductResponseDTO> productDTOs = products.stream()
                                                .map(Product::toDto)
                                                .toList();
        log.info("productsDTOs => "+productDTOs.toString());

        return productDTOs;
    }

    // 단건 조회
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable("id") Long id) {
        ProductResponseDTO productDTO = productService.getById(id);
        return productDTO;
    }

    //TODO: Response객체로 return 필요
    // 등록
    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody ProductCreateDTO productCreateDTO) {

        // TODO: 저장 성공, 실패 Exception 처리 필요 (Global Exception Handler)
        ProductResponseDTO saved = productService.create(productCreateDTO);
        return saved;
    }

    // 전체 수정
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable("id") Long productId, 
                                            @RequestBody ProductResponseDTO productDTO) {
        ProductResponseDTO updated = productService.update(productId, productDTO);
        return updated;
    }

    // 단건 삭제
    //TODO: 성공 / 실패 Response 객체로 return 필요
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    // 부분 수정
    @PatchMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable("id") Long id, 
                                            @RequestBody ProductPatchDTO patchDTO) {
        ProductResponseDTO updated = productService.update(id, patchDTO);
        return updated;
    }
}