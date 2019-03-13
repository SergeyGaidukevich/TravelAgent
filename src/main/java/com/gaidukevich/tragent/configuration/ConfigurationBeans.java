package com.gaidukevich.tragent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ResourceBundle;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("com.gaidukevich.tragent")
public class ConfigurationBeans {
    private static final String DATABASE = "database";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE);
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(resourceBundle.getString(DB_DRIVER));
        dataSource.setUrl(resourceBundle.getString(DB_URL));
        dataSource.setUsername(resourceBundle.getString(DB_USER));
        dataSource.setPassword(resourceBundle.getString(DB_PASSWORD));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(platformTransactionManager());
    }
}
