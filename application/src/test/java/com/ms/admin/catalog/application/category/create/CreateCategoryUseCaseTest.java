package com.ms.admin.catalog.application.category.create;

import com.ms.admin.catalog.domain.category.CategoryGateway;
import com.ms.admin.catalog.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private  DefaultCreateCategoryUseCase useCase;

    @Mock
    private CategoryGateway gateway;


    @Test
    public  void givenAValidaCommand_whenCallsCreateCategory_shouldReturnCategoryId(){
        final var expectedName = "filmes";
        final var expectedDescription = "Description sobre o filme";
        final var expectedActive = true;

        final var sut = CreateCategoryCommand.with(expectedName, expectedDescription, expectedActive);

        final CategoryGateway gateway =  Mockito.mock(CategoryGateway.class);

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var useCase = new DefaultCreateCategoryUseCase(gateway);

        final var company = useCase.execute(sut).get();

        Assertions.assertNotNull(company);
        Assertions.assertNotNull(company.id());

        Mockito.verify(gateway, times(1))
                .create(argThat(aCategory ->
                       Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.isNull(aCategory.getDeletedAt())
                ));
    }

    @Test
    public void givenAInvalidName_whenCallsCreateCategory_thenShouldReturnDomainException() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' is required";
        final var expectedErrorCount = 1;

        final var aCommand =
                CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAValidCommandWithInactiveCategory_whenCallsCreateCategory_shouldReturnInactiveCategoryId() {
        final var expectedName = "filmes";
        final var expectedDescription = "Description sobre o filme";
        final var expectedActive = false;

        final var sut = CreateCategoryCommand.with(expectedName, expectedDescription, expectedActive);

        final CategoryGateway gateway =  Mockito.mock(CategoryGateway.class);

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var useCase = new DefaultCreateCategoryUseCase(gateway);

        final var company = useCase.execute(sut).get();

        Assertions.assertNotNull(company);
        Assertions.assertNotNull(company.id());

        Mockito.verify(gateway, times(1))
                .create(argThat(aCategory ->
                {
                    return Objects.equals(expectedName, aCategory.getName())
                            && Objects.equals(expectedDescription, aCategory.getDescription())
                            && Objects.equals(expectedActive, aCategory.isActive())
                            && Objects.nonNull(aCategory.getId())
                            && Objects.nonNull(aCategory.getCreatedAt())
                            && Objects.nonNull(aCategory.getUpdatedAt())
                            && Objects.nonNull(aCategory.getDeletedAt());
                }));
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {
        final var expectedName = "filmes";
        final var expectedDescription = "Description sobre o filme";
        final var expectedErrorMessage = "'name' is required";
        final var expectedActive = true;
        final var expectedErrorCount = 1;

        final var sut = CreateCategoryCommand.with(expectedName, expectedDescription, expectedActive);

        when(gateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(sut).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(gateway, times(1)).create(any());
    }
}
