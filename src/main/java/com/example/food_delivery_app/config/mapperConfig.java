package com.example.food_delivery_app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}