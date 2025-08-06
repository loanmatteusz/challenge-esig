package com.esig.todo.infra.swagger;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "TODO API", version = "1.0", description = "API de gerenciamento de tarefas"))
public class OpenAPIConfig {
}
