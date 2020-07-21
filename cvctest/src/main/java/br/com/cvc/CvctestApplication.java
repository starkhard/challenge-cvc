package br.com.cvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableCaching
@EnableKafka
public class CvctestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvctestApplication.class, args);
	}

}
