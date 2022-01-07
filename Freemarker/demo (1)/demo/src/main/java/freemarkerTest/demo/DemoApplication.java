package freemarkerTest.demo;

import freemarkerTest.demo.domain.Board;
import freemarkerTest.demo.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner test(BoardRepository repository) {
		return args -> {

			for (int i = 0; i < 5; i++) {
			Board board = Board.builder()
					.title("test title : " + i)
					.contents("contents Test No : " + i)
					.build();
				repository.save(board);
			}
		};
	}

}
