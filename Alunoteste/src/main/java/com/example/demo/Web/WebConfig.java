package com.example.demo.Web;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeia a URL "/imagens/**" para o diretório de imagens estáticas
        registry.addResourceHandler("/imagens/**")
                .addResourceLocations("file:src/main/resources/static/imagens/");  // Caminho físico
    }
}