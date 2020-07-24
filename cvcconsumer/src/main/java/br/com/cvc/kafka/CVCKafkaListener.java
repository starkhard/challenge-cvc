package br.com.cvc.kafka;


import br.com.cvc.builder.Reservations;
import br.com.cvc.converter.CVCConveterReservation;
import br.com.cvc.service.impl.DefaultReservationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CVCKafkaListener {

    @Autowired
    private DefaultReservationImpl defaultReservation;

    @Autowired
    private CVCConveterReservation cvcConveterReservation;

    @KafkaListener(topics = "${cvc.topic.name}", groupId = "${cvc.topic.name}")
    public void kafkaListener(Reservations reservationBuilders) {
        cvcConveterReservation.converter(reservationBuilders);
    }
}


