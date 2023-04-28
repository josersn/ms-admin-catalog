package com.ms.admin.catalog.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    public  void givenAValidParams_whenCallNewCategory_thenInstantiateACategory () {

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public  void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError () {

        final String expectedName = null;
        final var expectedErrorMessage = "'name'is required";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var companyException = Assertions.assertThrows(DomainExectiption.class, () -> category.validate);

        Assertions.assertEquals(expectedErrorMessage,companyException.getErrors().get(0));
        Assertions.assertEquals(expectedErrorCount,companyException.getErrors().get().size());

    }
}
