package com.ms.admin.catalog.application;

public abstract class UnitUseCase<Payload> {
    public abstract void execute(Payload payload);
}
