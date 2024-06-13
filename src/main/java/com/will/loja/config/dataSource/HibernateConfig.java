package com.will.loja.config.dataSource;


import com.will.loja.config.context.SchemaBasedMultiTenantConnectionProvider;
import com.will.loja.config.context.TenantIdentifierResolver;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        return new SchemaBasedMultiTenantConnectionProvider();
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new TenantIdentifierResolver();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.multiTenancy", "SCHEMA");
        properties.put("hibernate.tenant_identifier_resolver", currentTenantIdentifierResolver());
        properties.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProvider());

        return builder
                .dataSource(dataSource)
                .packages("com.will.loja.model.entity")
                .persistenceUnit("my-persistence-unit")
                .properties(properties)
                .build();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}

