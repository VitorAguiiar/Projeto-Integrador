package com.example.demo.aluno;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer{
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        
        registry.addMapping("/**")  
                .allowedOrigins("http://localhost:8080", "https://cadaluno.azurewebsites.net")  // Permite o frontend local e o domínio de produção
                .allowedMethods("GET", "POST", "PUT", "DELETE")  
                .allowedHeaders("*")  
                .allowCredentials(true);  
    }


}
