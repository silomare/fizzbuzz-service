package io.github.silomare.practice.kafka.fizzbuzz.endpoints;

import org.springframework.stereotype.Component;

@Component("dummySink")
public class DummyResultSink implements ResultSink<String> {

  @Override
  public void put(String element) {
    System.out.println(element);// or to log output?
  }
}
