package freemarkerTest.demo;

import freemarkerTest.demo.domain.Board;
import freemarkerTest.demo.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(BoardRepository repository) {
		return args -> {
			Board test = Board.builder()
					.contents("test_1")
					.title("test_1")
					.regDate(new Date())
					.build();
			repository.save(test);
		};
	}

}
