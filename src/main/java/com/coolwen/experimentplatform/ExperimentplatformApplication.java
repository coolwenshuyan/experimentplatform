package com.coolwen.experimentplatform;

import com.coolwen.experimentplatform.config.ShiroConfig;
import com.coolwen.experimentplatform.dao.basedao.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.coolwen.experimentplatform"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@Import(ShiroConfig.class)
@EnableCaching
public class ExperimentplatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExperimentplatformApplication.class, args);

    }



}
