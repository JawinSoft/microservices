package com.spring.boot.msk.mobileclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MobileServiceClientApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(MobileServiceClientApplication.class, args);
	}
	
	
	
	/*@Bean
	public CommandLineRunner start() {
		return args -> {
			
			//String baseUrl = "http://localhost:8080/msk/mobiles";
			
			WebClient webClient = WebClient.builder().baseUrl(baseUrl +"mobiles/2").build();
			
			Mono<Response<Mobile>> allMobiles =  
					webClient
					.get()
					.accept(MediaType.APPLICATION_JSON)
			        .retrieve()
			        .bodyToMono(new ParameterizedTypeReference<Response<Mobile>>() {});
			
			Mobile mobile  = allMobiles.block().getResponse();
			
			System.out.println(mobile+" -- ");
			
			//Response<List<Mobile>> resonse = allMobiles.block();
			
			//resonse.getResponse().forEach(System.out::println);
		
	  };
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
		
	}*/

}
