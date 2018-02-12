package io.github.silomare.practice.kafka.fizzbuzz.endpoints;

public interface ResultSink<T> {
  void put(T element);
}
