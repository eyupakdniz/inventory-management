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

        Product product = new Product.ProductBuilder()
                .setName(newProduct.getName())
                .setRefCode(newProduct.getRefCode())
                .setDescription(newProduct.getDescription())
                .setQuantity(newProduct.getQuantity())
                .setPurchasePrice(newProduct.getPurchasePrice())
                .setCategory(category)
                .setProductTemplate(productTemplate)
                .build();

        Product saveBuild = productRepository.save(product);
        return ProductViewDto.of(saveBuild);
    }

    public ProductViewDto updateProduct(ProductUpdateDto updateProduct, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product not found with id: " + id));

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

        Product productBuild = new Product.ProductBuilder()
            .setName(updateProduct.getName())
            .setDescription(updateProduct.getDescription())
            .setQuantity(updateProduct.getQuantity())
            .setRefCode(updateProduct.getRefCode())
            .setPurchasePrice(updateProduct.getPurchasePrice())
            .setActive(updateProduct.getActive())
                .build();

        productBuild.setId(product.getId());
        Product saveBuild = productRepository.save(productBuild);
        return ProductViewDto.of(saveBuild);
    }
}