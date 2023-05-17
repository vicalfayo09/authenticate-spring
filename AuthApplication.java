package com.example.auth;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
        
        @Bean
        public CommandLineRunner commandLineRunner(ApplicationContext ctx){
            return args -> {
                System.out.println("Lets inspect the beans produced by our SpringBoot application");
           
            
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            
            for(String beanName: beanNames) {
                System.out.println(beanName);
            }
        };
        }

}
