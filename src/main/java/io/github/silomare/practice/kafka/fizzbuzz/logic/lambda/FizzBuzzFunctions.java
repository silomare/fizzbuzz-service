package io.github.silomare.practice.kafka.fizzbuzz.logic.lambda;

import java.util.function.Function;

public interface FizzBuzzFunctions {

  Function<Integer, String> FIZZBUZZ_RESULT = n -> "FizzBuzz";
  Function<Integer, String> FIZZ_RESULT = n -> "Fizz";
  Function<Integer, String> BUZZ_RESULT = n -> "Buzz";
  Function<Integer, String> DEFAULT_RESULT = n -> String.valueOf(n);
}
