package com.laura.universidad.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Configuracion implements WebMvcConfigurer{
    
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
