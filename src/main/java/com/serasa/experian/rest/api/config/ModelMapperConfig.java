package com.serasa.experian.rest.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações do ModelMapper, responsável por cuidar das instâncias.
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();	
	}
	
}
