package com.example.demo.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class postgesDatasource   { //return datasource

    @Bean
    @ConfigurationProperties("app.datasource")
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

//    @Bean(name = "flyway", initMethod = "migrate")
//    public Flyway flywayTheDestroyer() {
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(dataSource());
//        flyway.setBaselineOnMigrate(true);
//        flyway.clean();
//        return flyway;
//    }
}
