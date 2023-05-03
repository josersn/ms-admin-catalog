package com.ms.admin.catalog.application.category.update;

import com.ms.admin.catalog.domain.category.Category;
import com.ms.admin.catalog.domain.category.CategoryGateway;
import com.ms.admin.catalog.domain.category.CategoryID;
import com.ms.admin.catalog.domain.exceptions.DomainException;
import com.ms.admin.catalog.domain.validation.Error;
import com.ms.admin.catalog.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.*;
import static io.vavr.API.Try;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase{

    private CategoryGateway gateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Either<Notification, UpdatedCategoryOutput> execute(final UpdateCategoryCommand updateCategoryCommand) {
        final var id = CategoryID.from(updateCategoryCommand.id());
        final var name = updateCategoryCommand.name();
        final var description = updateCategoryCommand.description();
        final var isActive = updateCategoryCommand.isActive();

        final var category = gateway.findById(id)
                .orElseThrow(() -> notFound(id));

        final var notification = Notification.create();

        category.update(name, description, isActive).validate(notification);

        return  notification.hasError() ? Left(notification) : update(category);
    }

    private static DomainException notFound(CategoryID id) {
        return DomainException.with(new Error("Category with ID %s was not found".formatted(id.getValue())));
    }

    private Either<Notification, UpdatedCategoryOutput> update(Category category) {
        return Try(() -> this.gateway.update(category))
                .toEither()
                .bimap(Notification::create, UpdatedCategoryOutput::from);
    }
}
