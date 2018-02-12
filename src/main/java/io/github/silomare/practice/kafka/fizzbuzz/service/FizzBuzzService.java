package io.github.silomare.practice.kafka.fizzbuzz.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.silomare.practice.kafka.fizzbuzz.endpoints.ResultSink;
import io.github.silomare.practice.kafka.fizzbuzz.logic.FizzBuzzStrategy;

@Component
public class FizzBuzzService {

  @Autowired
  private FizzBuzzStrategy fizzBuzzTransformer;

  @Resource(name = "${fizzbuzz.sink.beanName}")
  private ResultSink<String> resultSink;

  public void process(Integer number) {
    String fizzBuzzResult = fizzBuzzTransformer.compute(number);
    resultSink.put(fizzBuzzResult);
  }
}
