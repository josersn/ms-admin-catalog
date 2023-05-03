package com.ms.admin.catalog.application.category.update;

import com.ms.admin.catalog.domain.category.Category;
import com.ms.admin.catalog.domain.category.CategoryID;

public record UpdatedCategoryOutput(
        CategoryID id
) {

    public static  UpdatedCategoryOutput from (final Category category) {
        return new UpdatedCategoryOutput(category.getId());
    }
}
