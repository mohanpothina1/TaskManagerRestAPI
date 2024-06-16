package com.example.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.taskmanager", "model", "repository", "controller","service"})
@EnableMongoRepositories(basePackages = "repository")
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
