package io.github.silomare.practice.kafka.fizzbuzz.logic.impl;

import io.github.silomare.practice.kafka.fizzbuzz.logic.FizzBuzzStrategy;

// Chain of responsibility pattern
public interface FizzBuzzChain extends FizzBuzzStrategy {

  FizzBuzzChain chainWith(FizzBuzzChain nextProcessor);
}
