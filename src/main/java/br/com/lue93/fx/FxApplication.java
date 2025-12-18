package br.com.lue93.fx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class FxApplication {

    private static final Logger logger = LoggerFactory.getLogger(FxApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FxApplication.class, args);

        run.getEnvironment().getSystemProperties().forEach((k, v) -> {
            logger.info(k + " = " + v);
        });
        System.getenv().forEach((key, value) -> {
            logger.info(key + " = " + value);
        });

    }

}
