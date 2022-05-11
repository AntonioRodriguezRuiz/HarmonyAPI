package com.harmonyapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "api")
public class Application {

    public static final Dotenv dotenv = Dotenv.load();
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
