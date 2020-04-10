package ru.otus.task3.mathtest;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.otus.task3.mathtest.service.IOService;

@Configuration
public class TestContextConfig {

@Bean
@Primary
IOService ioService() { return  Mockito.mock(IOService.class); }
}
