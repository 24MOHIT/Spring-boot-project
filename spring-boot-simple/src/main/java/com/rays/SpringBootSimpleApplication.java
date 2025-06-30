package com.rays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rays.common.FrontCtl;



@SpringBootApplication
public class SpringBootSimpleApplication {
	
	@Autowired
	private FrontCtl frontCtl;

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootSimpleApplication.class, args);
	}
	

}
