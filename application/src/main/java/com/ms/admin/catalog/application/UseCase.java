package com.ms.admin.catalog.application;

import com.ms.admin.catalog.domain.category.Category;

import java.util.UUID;

public abstract class UseCase<Payload , Response> {
    public abstract Response execute(Payload payload);
}