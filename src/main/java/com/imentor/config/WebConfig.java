package com.imentor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Change this path to your Python script's output folder
        registry.addResourceHandler("/audio/**")
                .addResourceLocations("file:/home/ronin/Development/iMentor-TTS/outputs/chat/")
                .setCachePeriod(0); // Disable caching for real-time access
    }
}
