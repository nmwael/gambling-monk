package com.slurry.springbrushup.appcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.slurry.springbrushup.services.MessagePrinter;
import com.slurry.springbrushup.services.MessageService;

@ComponentScan(basePackageClasses={MessageService.class})
@Configuration
public class AppContext {

	@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			public String getMessage() {
				return "Hello World!";
			}
		};
	}

}
