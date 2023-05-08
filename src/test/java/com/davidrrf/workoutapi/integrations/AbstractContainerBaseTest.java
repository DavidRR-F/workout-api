package com.davidrrf.workoutapi.integrations;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

public abstract class AbstractContainerBaseTest {
    static final PostgreSQLContainer POSTGRES_CONTAINER= new PostgreSQLContainer("postgres:latest")
            .withUsername("username")
            .withPassword("password")
            .withDatabaseName("workout");

    @BeforeAll
    public static void setUpClass() {
        POSTGRES_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
    }
}
