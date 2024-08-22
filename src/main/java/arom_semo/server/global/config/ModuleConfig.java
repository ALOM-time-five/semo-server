package arom_semo.server.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // Configure the ObjectMapper with necessary modules
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ParameterNamesModule()); // Handle parameter names
        objectMapper.findAndRegisterModules(); // Automatically register all other available modules

        return objectMapper;
    }
}
