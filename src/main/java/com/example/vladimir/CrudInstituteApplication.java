package com.example.vladimir;

import com.example.vladimir.models.Company;
import com.example.vladimir.services.grpc.FormationChequeServiceImpl;
import com.example.vladimir.services.impl.CompanyServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudInstituteApplication {
	 private final RabbitAdmin rabbitAdmin;
	 private final Queue queue;

	 @PostConstruct
	 public void declareQueue(){
		 rabbitAdmin.declareQueue(queue);
	 }

	public static void main(String[] args) {
		SpringApplication.run(CrudInstituteApplication.class, args);
	}

}
