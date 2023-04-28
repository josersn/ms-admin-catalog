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
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' is required"));
        }
    }
}
