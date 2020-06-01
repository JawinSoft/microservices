package com.spring.boot.msk.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.boot.msk.common.dto.Response;

import com.spring.boot.msk.mobile.model.Mobile;

import reactor.core.publisher.Mono;


@SpringBootApplication
public class MobileServiceClientApplication {
	
	@Value("${base-url}")
	private String baseUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(MobileServiceClientApplication.class, args);
	}
	
	
	
	@Bean
	public CommandLineRunner start() {
		return args -> {
			
			String baseUrl = "http://localhost:8080/msk/mobiles";
			
			WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
			
			Mono<Response<List<Mobile>>> allMobiles =  
					webClient
					.get()
					.accept(MediaType.APPLICATION_JSON)
			        .retrieve()
			        .bodyToMono(new ParameterizedTypeReference<Response<List<Mobile>>>() {});
			
			//Mobile mobile  = allMobile.block().getResponse();
			
			
			Response<List<Mobile>> resonse = allMobiles.block();
			
			resonse.getResponse().forEach(System.out::println);
		
	  };
	}
	
	
	/*@Bean
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
		
	}*/

}
