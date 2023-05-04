package com.ms.admin.catalog.infrastructure;

import com.ms.admin.catalog.domain.category.Category;
import com.ms.admin.catalog.infrastructure.category.persistence.Category.CategoryJpaEntity;
import com.ms.admin.catalog.infrastructure.category.persistence.Category.CategoryRepository;
import com.ms.admin.catalog.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
        System.out.println("Server running");
    }

    @Bean
    public ApplicationRunner runner(CategoryRepository repository) {
        return args -> {
            List<CategoryJpaEntity> all = repository.findAll();

            Category filmes = Category.newCategory("Kung Fu Panda 4", "Melhor filmes", true);

            repository.save(CategoryJpaEntity.from(filmes));

            repository.deleteAll();
        };
    }
}