package org.dev.quickshortapi.infraestructure.adapter.in.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.TimeUnit;

@Component("kafka")
public class KafkaHealthCheck implements HealthIndicator {

  private final KafkaTemplate<String, Object> KafkaTemplate;
  private String test_topic;

   public KafkaHealthCheck(KafkaTemplate<String, Object> kafkaTemplate, @Value("${spring.kafka.topic.name}") String test_topic) {
       this.test_topic = test_topic;
       KafkaTemplate = kafkaTemplate;
       this.getHealth(true);
    }

    @Override
    public Health health() {
        try {
            KafkaTemplate.send(test_topic, "❥").get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            return Health.down(e).build();
        }
        return Health.up().build();
    }
}
