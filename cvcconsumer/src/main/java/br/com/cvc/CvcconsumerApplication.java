package br.com.cvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude={MongoAutoConfiguration.class})
@EnableWebFlux
@EnableReactiveMongoRepositories
@EnableMongoAuditing
public class CvcconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvcconsumerApplication.class, args);
	}


}
