package com.ms.admin.catalog.domain.validation.handler;

import com.ms.admin.catalog.domain.exceptions.DomainException;
import com.ms.admin.catalog.domain.validation.Error;
import com.ms.admin.catalog.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

    private  Notification(List<Error> errors) {
        this.errors = errors;
    }

    public  static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public  static Notification create(final Error error) {
        return new Notification(new ArrayList<>()).append(error);
    }
    public  static Notification create(final Throwable t) {
        return create(new Error(t.getMessage()));
    }

    private final List<Error> errors;
    @Override
    public Notification append(Error error) {
        this.errors.add(error);
        return this;
    }

    @Override
    public Notification append(ValidationHandler handler) {
        this.errors.addAll(handler.getErrors());
        return this;
    }

    @Override
    public ValidationHandler validate(Validation validation) {
        try {
            validation.validate();
        } catch (final DomainException exception) {
            this.errors.addAll(exception.getErrors());
        }
        catch (final Throwable exceptionThrow) {
            this.errors.add(new Error(exceptionThrow.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }
}
