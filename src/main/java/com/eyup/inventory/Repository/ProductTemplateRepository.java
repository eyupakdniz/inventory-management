package com.eyup.inventory.repository;

import com.eyup.inventory.model.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTemplateRepository extends JpaRepository<ProductTemplate, Long> {
}
