package com.ms.admin.catalog.infrastructure.category;
import com.ms.admin.catalog.infrastructure.MySQLGatewayTest;
import com.ms.admin.catalog.infrastructure.category.persistence.Category.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySQLGatewayTest
public class CategoryMySQLGatewayTest {
    @Autowired
    private CategoryMySQLGateway categoryGateway;
    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    public void testInjectedDependencies() {

        Assertions.assertNotNull(categoryGateway);

        Assertions.assertNotNull(categoryRepository);
    }

}