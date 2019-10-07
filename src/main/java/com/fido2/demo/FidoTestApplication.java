package com.fido2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAutoConfiguration
@ComponentScan(basePackages={"com.fido2.demo"})
@EnableJpaRepositories(basePackages="com.fido2.demo.repository")
@EnableTransactionManagement
@EntityScan(basePackages="com.fido2.demo.entity")
@SpringBootApplication
public class FidoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FidoTestApplication.class, args);
    }

}
