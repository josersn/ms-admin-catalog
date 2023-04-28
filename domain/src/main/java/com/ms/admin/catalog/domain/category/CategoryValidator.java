package com.ms.admin.catalog.domain.category;

import com.ms.admin.catalog.domain.validation.Error;
import com.ms.admin.catalog.domain.validation.ValidationHandler;
import com.ms.admin.catalog.domain.validation.Validator;

public class CategoryValidator  extends Validator {

    private final Category category;
    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' is required"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' can't be empty"));
            return;
        }

        final var length = name.trim().length();

        if (length> 255 || length < 3) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
