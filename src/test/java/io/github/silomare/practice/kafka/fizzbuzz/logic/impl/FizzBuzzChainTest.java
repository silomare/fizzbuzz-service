package io.github.silomare.practice.kafka.fizzbuzz.logic.impl;

import org.junit.Assert;
import org.junit.Test;


public class FizzBuzzChainTest {

  private FizzBuzzChain fizzBuzz = FizzBuzzChainBuilder.build();

  @Test
  public void testFizz() throws Exception {
    Assert.assertEquals("Fizz", fizzBuzz.compute(6));
  }

  @Test
  public void testBuzz() throws Exception {
    Assert.assertEquals("Buzz", fizzBuzz.compute(20));
  }
  
  @Test
  public void testFizzBuzz() throws Exception {
    Assert.assertEquals("FizzBuzz", fizzBuzz.compute(30));
  }
  
  @Test
  public void testNotFizzAndNotBuzz() throws Exception {
    Assert.assertEquals("7", fizzBuzz.compute(7));
  }  
}
