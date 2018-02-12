package io.github.silomare.practice.kafka.fizzbuzz.logic.impl;

import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzFunctions.BUZZ_RESULT;
import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzFunctions.DEFAULT_RESULT;
import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzFunctions.FIZZBUZZ_RESULT;
import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzFunctions.FIZZ_RESULT;
import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzPredicates.IS_BUZZ;
import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzPredicates.IS_DEFAULT;
import static io.github.silomare.practice.kafka.fizzbuzz.logic.lambda.FizzBuzzPredicates.IS_FIZZ;

public class FizzBuzzChainBuilder {

  public static FizzBuzzChain build() {
    FizzBuzzChain fizzBuzz = new FizzBuzzChainHandler(IS_FIZZ.and(IS_BUZZ), FIZZBUZZ_RESULT);
    FizzBuzzChain fizz = new FizzBuzzChainHandler(IS_FIZZ, FIZZ_RESULT);
    FizzBuzzChain buzz = new FizzBuzzChainHandler(IS_BUZZ, BUZZ_RESULT);
    FizzBuzzChain dflt = new FizzBuzzChainHandler(IS_DEFAULT, DEFAULT_RESULT);

    fizzBuzz.chainWith(fizz).chainWith(buzz).chainWith(dflt);// chain of responsibilities

    return fizzBuzz;
  }

}
