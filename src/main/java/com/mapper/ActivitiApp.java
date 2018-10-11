package com.mapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@Controller
public class ActivitiApp {
	
	
	@RequestMapping("/")
	@ResponseBody()
    public String home() {
        return "Hello world";
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitiApp.class,args);
    }

}
