package com.ms.admin.catalog.application.category.create;

import com.ms.admin.catalog.domain.category.Category;
import com.ms.admin.catalog.domain.category.CategoryGateway;
import com.ms.admin.catalog.domain.validation.handler.Notification;
import com.ms.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.*;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand command) {
        final var name = command.name();
        final var description = command.description();
        final var isActive = command.isActive();

        final var notificationError = Notification.create();
        final  var category = Category.newCategory(name, description, isActive);
        category.validate(notificationError);

        return notificationError.hasError() ? Left(notificationError) : create(category);

    }

    private Either<Notification, CreateCategoryOutput> create(Category category) {
        return Try(() -> this.categoryGateway.create(category))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
