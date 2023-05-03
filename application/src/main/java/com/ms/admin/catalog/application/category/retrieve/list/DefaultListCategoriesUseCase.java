package com.ms.admin.catalog.application.category.retrieve.list;

import com.ms.admin.catalog.domain.category.CategoryGateway;
import com.ms.admin.catalog.domain.category.CategorySearchQuery;
import com.ms.admin.catalog.domain.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase{

    private CategoryGateway gateway;

    public DefaultListCategoriesUseCase(CategoryGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(CategorySearchQuery categorySearchQuery) {
        return gateway.findAll(categorySearchQuery).map(CategoryListOutput::from);
    }
}
