package com.ms.admin.catalog.infrastructure.category.persistence.Category;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<CategoryJpaEntity, String> {
}
