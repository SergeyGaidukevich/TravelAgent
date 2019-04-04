package com.gaidukevich.tragent.configuration;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Properties;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("com.gaidukevich.tragent")
@EnableAspectJAutoProxy
public class ConfigurationBeans {
    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String dbUserName;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.minConnection}")
    private String dbMinConnection;

    @Value("${db.maxConnection}")
    private String dbMaxConnection;

    @Value("${db.maxOpenPreparedStatements}")
    private String dbMaxOpenPreparedStatements;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);
        dataSource.setMinIdle(Integer.parseInt(dbMinConnection));
        dataSource.setMaxIdle(Integer.parseInt(dbMaxConnection));
        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(dbMaxOpenPreparedStatements));

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

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.gaidukevich.tragent.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public JpaTransactionManager geJpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
