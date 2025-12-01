package com.Library.LibraryManagement.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean
    ModelMapper mapper(){
        return new ModelMapper();
    }

}
