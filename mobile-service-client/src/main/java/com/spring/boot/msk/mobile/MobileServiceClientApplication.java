package com.spring.boot.msk.mobile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileServiceClientApplication {
	
	@Value("${base-url}")
	private String baseUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(MobileServiceClientApplication.class, args);
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
