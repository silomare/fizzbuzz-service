package io.github.silomare.practice.kafka.fizzbuzz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.silomare.practice.kafka.fizzbuzz.logic.FizzBuzzStrategy;
import io.github.silomare.practice.kafka.fizzbuzz.logic.impl.FizzBuzzChainBuilder;

@Configuration
public class FizzBuzzServiceConfig {

  @Bean
  public FizzBuzzStrategy fizzBuzzStrategy() {
    return FizzBuzzChainBuilder.build();
    // return new FizzBuzzSimple();
  }
}
