package io.javabrains.MovieInfoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
public class MovieInfoServiceApplication {

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		//return new RestTemplate();   //basic implementation

		//what do you do when a microService is slow, or not responding?
		//adding time out option 1
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(3000);
		return new RestTemplate(clientHttpRequestFactory);

		//use Hystrix
		//option2 adding a circuit breaker (use Hystrix), and when you cannot send the request, notify the caller
        //do this on the calling service


	}

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}

}
