package com.gaidukevich.tragent.configuration;

import com.gaidukevich.tragent.repository.jdbc.JdbcTemplateCountryRepository;
import com.gaidukevich.tragent.repository.jdbc.JdbcTemplateHotelRepository;
import com.gaidukevich.tragent.repository.jdbc.JdbcTemplateReviewRepository;
import com.gaidukevich.tragent.repository.jdbc.JdbcTemplateTourRepository;
import com.gaidukevich.tragent.repository.jdbc.JdbcTemplateUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ResourceBundle;

@Configuration
public class RepositoriesBeans {
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
    public JdbcTemplateCountryRepository countryRepository() {
        return new JdbcTemplateCountryRepository(jdbcTemplate());
    }

    @Bean
    public JdbcTemplateHotelRepository hotelRepository() {
        return new JdbcTemplateHotelRepository(jdbcTemplate());
    }

    @Bean
    public JdbcTemplateReviewRepository reviewRepository() {
        return new JdbcTemplateReviewRepository(jdbcTemplate());
    }

    @Bean
    public JdbcTemplateTourRepository tourRepository() {
        return new JdbcTemplateTourRepository(jdbcTemplate());
    }

    @Bean
    public JdbcTemplateUserRepository userRepository() {
        return new JdbcTemplateUserRepository(jdbcTemplate());
    }
}
