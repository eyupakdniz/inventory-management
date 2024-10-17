package com.eyup.inventory.controller;

import com.eyup.inventory.dto.ProductCreateDto;
import com.eyup.inventory.dto.ProductUpdateDto;
import com.eyup.inventory.dto.ProductViewDto;
import com.eyup.inventory.model.Product;
import com.eyup.inventory.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get")
    public ResponseEntity<Page<ProductViewDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Sayfa numarası sıfırdan küçük olamaz ve boyut sıfırdan büyük olmalıdır.");
        }

        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<ProductViewDto> products =  productService.getAllActive((org.springframework.data.domain.Pageable) pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductViewDto> getById(@PathVariable Long id) {
        ProductViewDto product = productService.getById(id);
        if (product == null) {
            throw new EntityNotFoundException("Ürün bulunamadı, id: " + id);
        }
        return ResponseEntity.ok(product);
    }


    @PostMapping("/create")
    public ResponseEntity<ProductViewDto> createProduct(@RequestBody ProductCreateDto newProduct) {
        try {
            ProductViewDto createdProduct = productService.createProduct(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Veritabanı hatası: " + ex.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductViewDto> updateProduct(@RequestBody ProductUpdateDto updateProductDto, @PathVariable Long id) {
        try {
            ProductViewDto updatedProduct = productService.updateProduct(updateProductDto, id);
            if (updatedProduct == null) {
                throw new EntityNotFoundException("Ürün bulunamadı, id: " + id);
            }
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Veritabanı hatası: " + ex.getMessage());
        }
    }

}
