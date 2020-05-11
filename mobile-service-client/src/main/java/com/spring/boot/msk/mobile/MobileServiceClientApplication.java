package com.spring.boot.msk.mobile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.spring.boot.msk.mobile.model.Mobile;

@SpringBootApplication
public class MobileServiceClientApplication {
	
	@Value("${base-url}")
	private String baseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceClientApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
	
	@Bean
	public CommandLineRunner start() {
		return args -> {
			System.out.println("Fetch Mobile Info Start >>>>>");
			
			ExecutorService threadPoll = Executors.newFixedThreadPool(5000);
			
			for(int i =0;i<5000;i++) {
				System.out.println("Count : "+i);
				Runnable task = () -> {
						Mobile response;
						try {
							response = restTemplate.getForObject(new URI(baseUrl+"mobiles/2"), Mobile.class);
							System.out.println("MObile Id 2 Response "+response);
						} catch (RestClientException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}//end catch
				};//end runnbale
				System.out.println("Submiting the Task..!");
			   threadPoll.submit(task);
			}//end for loop
			System.out.println("<<<<< Fetch Mobile Info End ");
			
			threadPoll.shutdown();
			
		};//end command Line runner
		
	}

}
