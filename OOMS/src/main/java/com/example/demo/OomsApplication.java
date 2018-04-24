package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.bo.Student;
import com.example.dao.StudentRepository;

@SpringBootApplication
@ComponentScan("com.example")
@EntityScan("com.example")
@EnableJpaRepositories("com.example")
public class OomsApplication {

	@Autowired
	StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(OomsApplication.class, args);
	}
/*
	@Override
	public void run(String... arg0) throws Exception {
		
		Student s = new Student();
		s.setName("Nitin");
		s = studentRepository.save(s);
		
		
		s = studentRepository.findOne(s.getId());
		s.setVersion(45);
		s.setName("sagar");
		s = studentRepository.save(s);
		
	}*/
}
