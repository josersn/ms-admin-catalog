package com.ms.admin.catalog.application.category.update;

import com.ms.admin.catalog.domain.category.Category;
import com.ms.admin.catalog.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway gateway;
    @Test
    public void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId() {
        final var expectedName = "filmes";
        final var expectedDescription = "Description sobre o filme";
        final var expectedActive = true;

        final var category = Category.newCategory("Fil", null, true);
        final var expectedId = category.getId();

        final var updateCategory = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedActive
        );

        when(gateway.findById(eq(category.getId())))
                .thenReturn(Optional.of(category));

        when(gateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var response = useCase.execute(updateCategory).get();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.id());

        verify(gateway, times(1)).findById(eq(expectedId));
        verify(gateway, times(1)).update(argThat(
                mockUpdatedCategory ->
                        Objects.equals(expectedName, mockUpdatedCategory.getName())
                                && Objects.equals(expectedDescription, mockUpdatedCategory.getDescription())
                                && Objects.equals(expectedActive, mockUpdatedCategory.isActive())
                                && Objects.equals(expectedId, mockUpdatedCategory.getId())
                                && Objects.equals(category.getCreatedAt(), mockUpdatedCategory.getCreatedAt())
                                && category.getUpdatedAt().isBefore(mockUpdatedCategory.getUpdatedAt())
                                && Objects.isNull(mockUpdatedCategory.getDeletedAt())
        ));

    }
}
