package com.eyup.inventory.Repository;

import com.eyup.inventory.model.Product;
import com.eyup.inventory.model.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTemplateRepository extends JpaRepository<ProductTemplate, Long> {
}
