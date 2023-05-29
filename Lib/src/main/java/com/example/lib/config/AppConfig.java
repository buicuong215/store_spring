package com.example.lib.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .setSkipNullEnabled(true);
        return modelMapper;
    }




}
