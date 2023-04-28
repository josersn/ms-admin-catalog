package com.ms.admin.catalog.domain.category;

import com.ms.admin.catalog.domain.exceptions.DomainException;
import com.ms.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
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
        final var expectedErrorMessage = "'name' is required";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var companyException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage,companyException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount,companyException.getErrors().size());

    }

    @Test
    public  void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError () {

        final String expectedName = " ";
        final var expectedErrorMessage = "'name' can't be empty";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var companyException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage,companyException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount,companyException.getErrors().size());

    }

    @Test
    public  void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError () {

        final String expectedName = "Fi";
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var companyException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage,companyException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount,companyException.getErrors().size());

    }

    @Test
    public  void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError () {

        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final String expectedName = """
                 É importante questionar o quanto a expansão dos mercados mundiais auxilia a preparação 
                 e a composição dos níveis de motivação departamental. Do mesmo modo, a estrutura atual 
                 da organização garante a contribuição de um grupo importante na determinação das novas proposições. 
                 Neste sentido, o desenvolvimento contínuo de distintas formas de atuação assume importantes posições 
                 """;
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var companyException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage,companyException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount,companyException.getErrors().size());

    }

    @Test
    public  void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveOk() {

        final String expectedName = "filmes";
        final var expectedDescription = "";
        final var expectedIsActive = true;


        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

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
    public  void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveOk() {

        final String expectedName = "filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;


        final var category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNotNull(category.getDeletedAt());
    }
}
