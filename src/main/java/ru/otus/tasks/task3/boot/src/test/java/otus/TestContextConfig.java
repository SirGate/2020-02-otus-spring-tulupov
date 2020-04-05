package otus;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import otus.service.IOService;

@Configuration
public class TestContextConfig {

@Bean
@Primary
IOService ioService() { return  Mockito.mock(IOService.class); }
}
