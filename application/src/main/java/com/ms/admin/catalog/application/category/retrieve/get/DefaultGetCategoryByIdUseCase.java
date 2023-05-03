package com.ms.admin.catalog.application.category.retrieve.get;

import com.ms.admin.catalog.domain.category.CategoryGateway;
import com.ms.admin.catalog.domain.category.CategoryID;
import com.ms.admin.catalog.domain.exceptions.DomainException;
import com.ms.admin.catalog.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase{

    private CategoryGateway gateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public CategoryOutput execute(final String id) {
        final var categoryId = CategoryID.from(id);
        return this.gateway.findById(categoryId)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(categoryId));
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(anId.getValue()))
        );
    }
}
