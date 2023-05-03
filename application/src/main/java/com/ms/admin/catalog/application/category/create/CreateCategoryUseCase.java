package com.ms.admin.catalog.application.category.create;

import com.ms.admin.catalog.application.UseCase;
import com.ms.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
