package com.ms.admin.catalog.application.category.retrieve.get;

import com.ms.admin.catalog.domain.category.Category;
import com.ms.admin.catalog.domain.category.CategoryGateway;
import com.ms.admin.catalog.domain.category.CategoryID;
import com.ms.admin.catalog.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCategoryCategoryByIdTest {
    @Mock
    private CategoryGateway gateway;

    @InjectMocks
    private DefaultGetCategoryByIdUseCase useCase;

    @BeforeEach
    void cleanUp () {
        reset(gateway);
    }

    @Test
    public void givenAValidId_whenCallsGetCategory_shouldReturnCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var category =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var expectedId = category.getId();

        when(gateway.findById(category.getId()))
                .thenReturn(Optional.of(category.clone()));

        final var categoryFound = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(CategoryOutput.from(category), categoryFound);
        Assertions.assertEquals(expectedName, categoryFound.name());
        Assertions.assertEquals(expectedDescription, categoryFound.description());
        Assertions.assertEquals(expectedIsActive, categoryFound.isActive());
        Assertions.assertEquals(expectedId, categoryFound.id());

    }

    @Test
    public void givenAInvalidId_whenCallsGetCategory_shouldReturnNotFound() {
        final var expectedErrorMessage = "Category with ID 123 was not found";
        final var expectedId = CategoryID.from("123");


        final var categoryException = Assertions.assertThrows(DomainException.class,
                () -> useCase.execute(expectedId.getValue())) ;

        Assertions.assertEquals(expectedErrorMessage, categoryException.getMessage());

    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final var expectedErrorMessage = "Gateway error";
        final var expectedId = CategoryID.from("123");

        when(gateway.findById(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
