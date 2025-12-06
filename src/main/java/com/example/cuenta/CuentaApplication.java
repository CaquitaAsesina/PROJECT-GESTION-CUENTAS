package com.example.cuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CuentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentaApplication.class, args);
		System.out.println("http://localhost:8080/users");
		System.out.println("http://localhost:8080/gmails");
	}

}
