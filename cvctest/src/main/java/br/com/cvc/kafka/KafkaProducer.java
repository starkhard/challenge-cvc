package br.com.cvc.kafka;

import br.com.cvc.builder.Reservations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Reservations> kafkaTemplate;


    public KafkaProducer(KafkaTemplate<String, Reservations> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sent(String topicName, Reservations reservations) {

        ListenableFuture<SendResult<String, Reservations>> future =
                kafkaTemplate.send(topicName, reservations);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Reservations>>() {

            @Override
            public void onSuccess(SendResult<String, Reservations> result) {
                log.info("success data sent !" + topicName);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("error to sent values to kafka  " + ex.getMessage());
            }
        });
    }
}
