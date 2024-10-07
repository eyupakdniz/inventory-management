package com.eyup.inventory.service;

import com.eyup.inventory.exception.EntityNotFoundException;
import com.eyup.inventory.repository.CategoryRepository;
import com.eyup.inventory.repository.ProductRepository;
import com.eyup.inventory.repository.ProductTemplateRepository;
import com.eyup.inventory.dto.ProductCreateDto;
import com.eyup.inventory.dto.ProductUpdateDto;
import com.eyup.inventory.dto.ProductViewDto;
import com.eyup.inventory.model.Category;
import com.eyup.inventory.model.Product;
import com.eyup.inventory.model.ProductTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTemplateRepository productTemplateRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductTemplateRepository productTemplateRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productTemplateRepository = productTemplateRepository;
    }

    public Page<ProductViewDto> getAllActive(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable).map(ProductViewDto::of);
    }

    public ProductViewDto getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product not found with id: " + id));
        return ProductViewDto.of(product);
    }

    public ProductViewDto createProduct(ProductCreateDto newProduct) {
        Category category = categoryRepository.findById(newProduct.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + newProduct.getCategoryId()));

        ProductTemplate productTemplate = productTemplateRepository.findById(newProduct.getProductTemplateId())
                .orElseThrow(() -> new EntityNotFoundException("Product template not found with id: " + newProduct.getProductTemplateId()));

        Product product = new Product(
                newProduct.getName(),
                newProduct.getRefCode(),
                newProduct.getDescription(),
                newProduct.getQuantity(),
                newProduct.getPurchasePrice(),
                category,
                productTemplate
        );

        final Product savedProduct = productRepository.save(product);
        return ProductViewDto.of(savedProduct);
    }

    public ProductViewDto updateProduct(ProductUpdateDto updateProduct, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product not found with id: " + id));

        product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setQuantity(updateProduct.getQuantity());
        product.setRefCode(updateProduct.getRefCode());
        product.setPurchasePrice(updateProduct.getPurchasePrice());
        product.setActive(updateProduct.getActive());

        if (updateProduct.getProductTemplateId() != null) {
            ProductTemplate productTemplate = productTemplateRepository.findById(updateProduct.getProductTemplateId())
                    .orElseThrow(() -> new EntityNotFoundException("Product template not found with id: " + updateProduct.getProductTemplateId()));
            product.setProductTemplate(productTemplate);
        }

        if (updateProduct.getCategoryId() != null) {
            Category category = categoryRepository.findById(updateProduct.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + updateProduct.getCategoryId()));
            product.setCategory(category);
        }

        return ProductViewDto.of(product);
    }
}