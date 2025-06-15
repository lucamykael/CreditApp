package com.picpay.credit.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.credit.models.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  public void sendUserCreatedEvent(UserCreatedEvent event) throws JsonProcessingException{
    String json = objectMapper.writeValueAsString(event);
    kafkaTemplate.send("user-created", json);
  }
}
