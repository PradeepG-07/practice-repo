package com.pradeep.student_crud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.pradeep.student_crud")
@EnableWebMvc
public class AppConfig {
}
