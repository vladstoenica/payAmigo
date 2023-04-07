package com.example.payAmigo;

import com.example.payAmigo.entity.User;
import com.example.payAmigo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayAmigoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayAmigoApplication.class, args);

	}

}
