package io.github.silomare.practice.kafka.fizzbuzz.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import io.github.silomare.practice.kafka.fizzbuzz.endpoints.ResultSink;
import io.github.silomare.practice.kafka.fizzbuzz.logic.FizzBuzzStrategy;

@RunWith(MockitoJUnitRunner.class)
public class FizzBuzzServiceTest {
  @Mock
  private FizzBuzzStrategy fizzBuzzTransformer;

  @Mock
  private ResultSink<String> resultSink;
  @InjectMocks
  private FizzBuzzService fizzBuzzService;

  @Test
  public void testProcess() throws Exception {
    // prepare test data and mock dependencies
    Integer testInput = 3;
    Mockito.when(fizzBuzzTransformer.compute(testInput)).thenReturn("Fizz");
    
    // exercise
    fizzBuzzService.process(testInput);
    
    // verify expectations
    Mockito.verify(resultSink).put("Fizz");
  }

}
