package io.github.silomare.practice.kafka.fizzbuzz.logic.impl;

import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzPredicates.IS_BUZZ;
import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzPredicates.IS_FIZZ;

import io.github.silomare.practice.kafka.fizzbuzz.logic.FizzBuzzStrategy;

public class FizzBuzzSimple implements FizzBuzzStrategy {

  @Override
  public String compute(int n) {
    String result = String.valueOf(n);// default;

    if (IS_FIZZ.and(IS_BUZZ).test(n)) {
      result = "FizzBuzz";
    } else if (IS_FIZZ.test(n)) {
      result = "Fizz";
    } else if (IS_BUZZ.test(n)) {
      result = "Buzz";
    }
    return result;
  }

}
