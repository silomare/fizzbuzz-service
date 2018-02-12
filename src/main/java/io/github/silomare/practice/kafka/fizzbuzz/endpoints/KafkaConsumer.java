package io.github.silomare.practice.kafka.fizzbuzz.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import io.github.silomare.practice.kafka.fizzbuzz.service.FizzBuzzService;

@Component
public class KafkaConsumer {

  @Autowired
  private FizzBuzzService fizzBuzzService;

  @KafkaListener(topics = "${kafka.topic.input}")
  public void processMessage(Integer number) {
    fizzBuzzService.process(number);
  }
}
