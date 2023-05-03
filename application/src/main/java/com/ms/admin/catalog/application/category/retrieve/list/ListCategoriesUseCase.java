package com.ms.admin.catalog.application.category.retrieve.list;

import com.ms.admin.catalog.application.UseCase;
import com.ms.admin.catalog.domain.category.CategorySearchQuery;
import com.ms.admin.catalog.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
