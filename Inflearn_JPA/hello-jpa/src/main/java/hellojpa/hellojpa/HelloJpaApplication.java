package hellojpa.hellojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class HelloJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloJpaApplication.class, args);
	}

}
