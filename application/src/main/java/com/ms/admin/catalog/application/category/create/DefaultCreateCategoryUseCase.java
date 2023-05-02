package com.ms.admin.catalog.application.category.create;

import com.ms.admin.catalog.domain.category.Category;
import com.ms.admin.catalog.domain.category.CategoryGateway;
import com.ms.admin.catalog.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    public CreateCategoryOutput execute(final CreateCategoryCommand command) {
        final var name = command.name();
        final var description = command.description();
        final var isActive = command.isActive();

        final  var category = Category.newCategory(name, description, isActive);

        category.validate(new ThrowsValidationHandler());

        this.categoryGateway.create(category);

        return CreateCategoryOutput.from(category);
    }
}
