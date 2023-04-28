package com.ms.admin.catalog.application;

import com.ms.admin.catalog.domain.category.Category;

import java.util.UUID;

public class UseCase {
    public Category execute () {
        return  Category.newCategory("nome", "description", false);
    }
}