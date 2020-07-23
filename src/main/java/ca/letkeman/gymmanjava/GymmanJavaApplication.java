package ca.letkeman.gymmanjava;

import ca.letkeman.gymmanjava.service.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GymmanJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymmanJavaApplication.class, args);
	}

}
