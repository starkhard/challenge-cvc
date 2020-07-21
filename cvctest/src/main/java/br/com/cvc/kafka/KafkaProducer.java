package br.com.cvc.kafka;

import br.com.cvc.builder.Reservations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Reservations> kafkaTemplate;


    public KafkaProducer(KafkaTemplate<String, Reservations> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sent(String topicName, Reservations reservations) {
        kafkaTemplate.send(topicName, reservations);
    }
}
