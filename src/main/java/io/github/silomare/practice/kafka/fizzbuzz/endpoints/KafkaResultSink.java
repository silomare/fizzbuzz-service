package io.github.silomare.practice.kafka.fizzbuzz.endpoints;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component("kafkaSink")
public class KafkaResultSink implements ResultSink<String> {

  @Resource(name = "resultKafkaTemplate")
  private KafkaTemplate<String, String> kafkaTemplate;

  @Value("${kafka.topic.result}")
  private String kafkaTopic;

  @Override
  public void put(String element) {
    kafkaTemplate.send(kafkaTopic, element);
  }
}
