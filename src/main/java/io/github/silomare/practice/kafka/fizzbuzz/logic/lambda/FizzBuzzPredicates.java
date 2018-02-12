package io.github.silomare.practice.kafka.fizzbuzz.logic.lambda;

import java.util.function.Predicate;

public interface FizzBuzzPredicates {

  Predicate<Integer> IS_FIZZ = n -> n % 3 == 0;
  Predicate<Integer> IS_BUZZ = n -> n % 5 == 0;
  Predicate<Integer> IS_DEFAULT = n -> true;

}
