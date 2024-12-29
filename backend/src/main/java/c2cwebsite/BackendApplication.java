package c2cwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"c2cwebsite.model", "c2cwebsite.pojo", "c2cwebsite.controller",
								"c2cwebsite.persistance", "c2cwebsite.persistance.repository"})
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
