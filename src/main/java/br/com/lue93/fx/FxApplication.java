package br.com.lue93.fx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxApplication.class, args);
	}

}
