package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}
//	lazy로딩시 proxy를 읽는 오류 방지하기 위함
//	어차피 엔티티를 노출했을때 사용하는 방식임
	@Bean
	Hibernate5Module hibernate5Module() {
//		lazy로딩불러오기옵션
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);

		return new Hibernate5Module();

	}



}
