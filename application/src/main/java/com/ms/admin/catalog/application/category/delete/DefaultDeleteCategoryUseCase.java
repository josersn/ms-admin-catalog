package com.ms.admin.catalog.application.category.delete;

import com.ms.admin.catalog.domain.category.CategoryGateway;
import com.ms.admin.catalog.domain.category.CategoryID;

import java.util.Objects;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase{

    private CategoryGateway gateway;

    public DefaultDeleteCategoryUseCase(CategoryGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public void execute(final String id) {
        this.gateway.deleteById(CategoryID.from(id));
    }
}
