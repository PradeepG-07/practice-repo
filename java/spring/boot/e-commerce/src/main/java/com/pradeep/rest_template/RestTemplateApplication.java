package com.pradeep.rest_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApplication {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}
}
