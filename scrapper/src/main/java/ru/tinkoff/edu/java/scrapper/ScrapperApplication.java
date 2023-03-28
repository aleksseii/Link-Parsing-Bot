package ru.tinkoff.edu.java.scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(ApplicationConfig.class)
public class ScrapperApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ScrapperApplication.class, args);
        ApplicationConfig config = context.getBean(ApplicationConfig.class);
        System.out.println(config);
    }
}
