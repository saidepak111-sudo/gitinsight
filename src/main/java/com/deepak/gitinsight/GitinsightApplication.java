package com.deepak.gitinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GitinsightApplication {
   
	public static void main(String[] args) {
		SpringApplication.run(GitinsightApplication.class, args);
	}

}
