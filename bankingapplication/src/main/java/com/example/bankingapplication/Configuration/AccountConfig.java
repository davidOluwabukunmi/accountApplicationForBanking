package com.example.bankingapplication.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {
   @Bean
    public ModelMapper modelmaper(){
       return new ModelMapper();
   }
}
