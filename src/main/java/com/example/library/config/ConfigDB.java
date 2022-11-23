package com.example.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ConfigDB {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    CommandLineRunner initDatabase() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                jdbcTemplate.execute("Create table Product (" +
                        "    id bigint auto_increment not null," +
                        "    title varchar(255) not null ," +
                        "    category varchar(255) not null ," +
//                        "    publishing varchar(255) not null ," +
                        "    theme varchar(255) not null ," +
                        "    edition varchar(255) not null ," +
//                        "    author varchar(255) not null ," +
                        "    release_date timestamp not null ," +
                        "    primary key(id)\n" +
                        ");");
            }
        };
    }
}
