package com.slurry.springbrushup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.slurry.springbrushup.services.MessagePrinter;
import com.slurry.springbrushup.services.MessageService;


public class App {

  

  public static void main(String[] args) {
      ApplicationContext context = 
          new AnnotationConfigApplicationContext(com.slurry.springbrushup.appcontext.AppContext.class);
      MessagePrinter printer = context.getBean(MessagePrinter.class);
      printer.printMessage();
  }
}