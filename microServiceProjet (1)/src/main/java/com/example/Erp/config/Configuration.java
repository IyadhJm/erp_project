package com.example.Erp.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


public class Configuration {


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }




}
