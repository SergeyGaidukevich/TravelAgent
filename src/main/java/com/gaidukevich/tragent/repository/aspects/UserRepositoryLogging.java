package com.gaidukevich.tragent.repository.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserRepositoryLogging {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryLogging.class);

    @Pointcut("execution(* com.gaidukevich.tragent.repository.impl.GenericRepository.getById(Long))" +
            " && args(id) && bean(userRepository)")
    public void getById(Long id) {
    }

    @Pointcut("execution(* com.gaidukevich.tragent.repository.jdbc.JdbcTemplateUserRepository" +
            ".getById(Long)) && args(id)")
    public void getByIdJdbc(Long id) {
    }

    @Before(value = "getById(id)", argNames = "id")
    public void isRunningGetById(Long id) {
        LOGGER.info("UserRepositoryImpl.getById(..) is running with ID = " + id);
    }

    @Before(value = "getByIdJdbc(id)", argNames = "id")
    public void isRunningGetByIdJdbc(Long id) {
        LOGGER.info("JdbcTemplateUserRepository.getById(..) is running with ID = " + id);
    }
}
