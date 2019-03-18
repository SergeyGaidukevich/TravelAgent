package com.gaidukevich.tragent.repository.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CountryRepositoryLogging {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRepositoryLogging.class);

    @Pointcut("execution(* com.gaidukevich.tragent.repository.impl.GenericRepository.getById(Long))" +
            " && args(id) && bean(countryRepository)")
    public void getById(Long id) {
    }

    @Pointcut("execution(* com.gaidukevich.tragent.repository.jdbc.JdbcTemplateCountryRepository" +
            ".getById(Long)) && args(id)")
    public void getByIdJdbc(Long id) {
    }

    @Before(value = "getById(id)", argNames = "id")
    public void isRunningGetById(Long id) {
        LOGGER.info("CountryRepositoryImpl.getById(..) is running with ID = " + id);
    }

    @Before(value = "getByIdJdbc(id)", argNames = "id")
    public void isRunningGetByIdJdbc(Long id) {
        LOGGER.info("JdbcTemplateCountryRepository.getById(..) is running with ID = " + id);
    }
}
