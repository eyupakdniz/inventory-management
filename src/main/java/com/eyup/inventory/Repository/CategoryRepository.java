package com.eyup.inventory.Repository;

import com.eyup.inventory.model.Category;
import com.eyup.inventory.model.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
