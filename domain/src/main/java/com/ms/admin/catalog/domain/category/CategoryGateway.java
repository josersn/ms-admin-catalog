package com.ms.admin.catalog.domain.category;

import com.ms.admin.catalog.domain.pagination.Pagination;
import java.util.Optional;

public interface CategoryGateway {
    Category create(Category category);
    void deleteById(CategoryID Id);

    Optional<Category> findById(CategoryID Id);

    Category update(Category category);

    Pagination<Category> findAll(CategorySearchQuery query);

}
