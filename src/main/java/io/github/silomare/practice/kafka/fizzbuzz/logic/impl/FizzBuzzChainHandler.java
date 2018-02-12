package io.github.silomare.practice.kafka.fizzbuzz.logic.impl;

import java.util.function.Function;
import java.util.function.Predicate;

public class FizzBuzzChainHandler implements FizzBuzzChain {

  // private static final Logger log = LoggerFactory.getLogger(FizzBuzzProcessor.class);

  private FizzBuzzChain chain;

  private Predicate<Integer> acceptCriteria;
  private Function<Integer, String> resultProducer;

  public FizzBuzzChainHandler(Predicate<Integer> acceptCriteria,
      Function<Integer, String> resultProducer) {
    super();
    this.acceptCriteria = acceptCriteria;
    this.resultProducer = resultProducer;
  }

  @Override
  public FizzBuzzChain chainWith(FizzBuzzChain nextHandler) {
    this.chain = nextHandler;
    return nextHandler;
  }

  @Override
  public String compute(int n) {
    String result;
    if (this.acceptCriteria.test(n)) {
      result = this.resultProducer.apply(n);
    } else {
      if (this.chain != null) {
        result = this.chain.compute(n);
      } else {
        throw new RuntimeException("Unable to handle this element: " + n);// TODO rework to proper
                                                                          // exception handling
      }
    }
    return result;
  }

}
