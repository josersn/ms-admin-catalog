package com.ms.admin.catalog.application.category.create;

import com.ms.admin.catalog.domain.category.Category;
import com.ms.admin.catalog.domain.category.CategoryID;

public record CreateCategoryOutput(CategoryID id) {
    public static CreateCategoryOutput from (final Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
