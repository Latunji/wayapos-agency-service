
package org.wayapos.agencyService.digitalIdentityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class WayaPosDigitalIdentityServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WayaPosDigitalIdentityServiceApplication.class, args);
	}
	
	@Bean
	//@LoadBalanced
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

}
