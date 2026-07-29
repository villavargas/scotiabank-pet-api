package com.example.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) {
		// Trust corporate proxy SSL certificates via the Windows certificate store
		System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");
		SpringApplication.run(ExamApplication.class, args);
	}

}
