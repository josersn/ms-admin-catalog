package com.ms.admin.catalog.domain.validation.handler;

import com.ms.admin.catalog.domain.exceptions.DomainException;
import com.ms.admin.catalog.domain.validation.Error;
import com.ms.admin.catalog.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error error) {
        throw DomainException.with(error);
    }

    @Override
    public ValidationHandler append(final ValidationHandler handler) {

        throw DomainException.with(handler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation validation) {
        try {
            validation.validate();
        } catch (final Exception error) {
            throw DomainException.with(new Error(error.getMessage()));
        }

        return  this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
