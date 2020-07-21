package br.com.cvc.kafka;


import br.com.cvc.builder.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.KafkaException;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class KafkaProducerTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void testKafkaSampleSentDTO() {
        Reservations rb = generateReservationBuilder();
        kafkaProducer.sent("cvc.reservations", (rb));
    }

    @Test
    public void testkafkaTimeouPersistDTO() {
        Assertions.assertTimeout(Duration.ofMillis(500), () -> kafkaProducer.sent("cvc.reservations", generateReservationBuilder()));
    }

    @Test
    public void testKafkaPossibleErrors() {
        Assertions.assertThrows(KafkaException.class, () -> kafkaProducer.sent("", generateReservationBuilder()));
        Assertions.assertThrows(KafkaException.class, () -> kafkaProducer.sent("", null));

    }

    private Reservations generateReservationBuilder() {
        Reservations reservations = new Reservations();
        Set<RoomsBuilder> setRooms = new HashSet<>();
        CategoryBuilder categoryBuilder = CategoryBuilder.builder().name("teste categoria ").build();
        setRooms.add(RoomsBuilder.builder().id("1").category(categoryBuilder).build());

        PriceBuilder priceBuilder = PriceBuilder.builder()
                .pricePerDayChild(new BigDecimal(32322))
                .pricePerDayAdult(new BigDecimal(444122))
                .build();

        ReservationBuilder rb = ReservationBuilder.builder()
                .city("1212")
                .id("12")
                .totalPrice(new BigDecimal(1222))
                .rooms(setRooms)
                .priceDetails(priceBuilder)
                .build();
        reservations.setReservationBuilders(Arrays.asList(rb));

        return reservations;
    }
}
