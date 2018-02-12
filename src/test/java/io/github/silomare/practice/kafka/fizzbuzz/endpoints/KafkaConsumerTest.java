package io.github.silomare.practice.kafka.fizzbuzz.endpoints;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import io.github.silomare.practice.kafka.fizzbuzz.service.FizzBuzzService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class KafkaConsumerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerTest.class);

  @Value("${kafka.topic.input}")
  private String resultTopic;
  private static String RECEIVER_TOPIC = "receiver.t";// TODO: class/static embeddedKafka is forcing to "duplicate" the injected topic name as an static member

  @Value("${kafka.consumer.group-id}")
  private String consumerGroupId;
  
  @Autowired
  private KafkaConsumer receiver;
  
  @Mock
  private FizzBuzzService mockService;
  
  
  private KafkaTemplate<String, Integer> template;

  @Autowired
  private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

  @ClassRule
  public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RECEIVER_TOPIC);

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    // set up the Kafka producer properties
    Map<String, Object> senderProperties =
        KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
    senderProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    senderProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
    // create a Kafka producer factory
    ProducerFactory<String, Integer> producerFactory =
        new DefaultKafkaProducerFactory<String, Integer>(senderProperties);

    // create a Kafka template
    template = new KafkaTemplate<String, Integer>(producerFactory);
    // set the default topic to send to
    template.setDefaultTopic(RECEIVER_TOPIC);

    // wait until the partitions are assigned
    for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
        .getListenerContainers()) {
      ContainerTestUtils.waitForAssignment(messageListenerContainer,
          embeddedKafka.getPartitionsPerTopic());
    }
    
  }

  
  @Test
  public void testReceive() throws Exception {
    ReflectionTestUtils.setField(receiver, "fizzBuzzService", mockService);
    // send the message
    int numberToSend = 99;
    template.sendDefault(numberToSend);
    LOGGER.debug("test-sender sent message='{}'", numberToSend);
    // check that the message was received
    Thread.sleep(1000);//TODO: count down latch on listener instead?
    Mockito.verify(this.mockService).process(numberToSend);
  }
}