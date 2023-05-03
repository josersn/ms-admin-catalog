package com.ms.admin.catalog.application.category.update;

import com.ms.admin.catalog.application.UseCase;
import com.ms.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public  abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdatedCategoryOutput>> {

}
