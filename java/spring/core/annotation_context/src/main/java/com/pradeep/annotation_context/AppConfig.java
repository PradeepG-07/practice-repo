package com.pradeep.annotation_context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public ExternalModel externalModel() {
        return new ExternalModel();
    }

    // Syntax

    // @Bean("customBeanName")
    // <<access-modifier>> <<return-type>> <<bean-name>>(){
    // }
}
