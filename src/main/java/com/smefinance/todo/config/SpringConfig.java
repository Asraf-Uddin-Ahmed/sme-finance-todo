package com.smefinance.todo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {

	@Bean
	@Scope(value = "prototype")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
    @Scope(value = "prototype")
    public ModelMapper modelMapperStrict() {
        final ModelMapper modelMapper = modelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

}
