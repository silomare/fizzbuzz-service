package io.github.silomare.practice.kafka.fizzbuzz.logic.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class FizzBuzzChainHandlerTest {

  private FizzBuzzChainHandler handler;
  
  @Before
  public void buildProcessorChain() throws Exception {
    handler = new FizzBuzzChainHandler(x -> x > 1, x -> "is greater than 1: " + x);
    FizzBuzzChainHandler secondHandler = new FizzBuzzChainHandler(x -> x == 1, x -> "is equal to 1: " + x);
    handler.chainWith(secondHandler);
  }

  @Test
  public void computesIfFirstCanHanle() throws Exception {
    // prepare test data
    int testInput = 2;
    
    // exercise
    String actualOut = handler.compute(testInput);
    
    // verify
    Assert.assertEquals("is greater than 1: 2", actualOut);
  }

  @Test
  public void computesIfSecondCanHanle() throws Exception {
    // prepare test data
    int testInput = 1;
    
    // exercise
    String actualOut = handler.compute(testInput);
    
    // verify
    Assert.assertEquals("is equal to 1: 1", actualOut);
  }

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
  @Test
  public void failsIfNoneCanHanle() throws Exception {
    // setup failure expectation
    thrown.expect(RuntimeException.class);
    thrown.expectMessage("Unable to handle this element: 0");
    
    // prepare test data
    int testInput = 0;
    
    // exercise
    handler.compute(testInput);
  }
}
