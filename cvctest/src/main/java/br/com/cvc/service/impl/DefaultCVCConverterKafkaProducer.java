package br.com.cvc.service.impl;

import br.com.cvc.builder.*;
import br.com.cvc.calculator.CVCCalculator;
import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.dto.HotelDTO;
import br.com.cvc.kafka.KafkaProducer;
import br.com.cvc.service.CVCConverterKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultCVCConverterKafkaProducer implements CVCConverterKafkaProducer {


    @Autowired
    private KafkaProducer kafkaProducer;
    @Value("${cvc.topic.name}")
    private String topic;
    @Autowired
    private CVCCalculator cvcCalculator;

    @Override
    public List<ReservationBuilder> converter(List<HotelDTO> hotelDTOS, CVCReservationDTO cvcReservationDTO) {

        Reservations reservations = new Reservations();

        List<ReservationBuilder> reservationBuilders = hotelDTOS.stream()
                .map(response -> {
                    ReservationBuilder reservationBuilder = ReservationBuilder.builder()
                            .id(response.getId())
                            .city(response.getCityName())
                            .rooms(response.getRooms().stream().map(r -> {
                                RoomsBuilder roomsBuilder = RoomsBuilder.builder()
                                        .id(r.getRoomID())
                                        .category(CategoryBuilder.builder().name(r.getCategoryName()).build())
                                        .build();
                                return roomsBuilder;
                            }).collect(Collectors.toSet()))
                            .totalPrice(cvcCalculator.calculateTaxByPerson(response, cvcReservationDTO))
                            .priceDetails(response.getRooms().stream()
                                    .map(x -> {
                                        PriceBuilder pb = PriceBuilder.builder()
                                                .pricePerDayAdult(x.getPrice().getPriceAdult())
                                                .pricePerDayChild(x.getPrice().getPriceChild())
                                                .build();
                                        return pb;
                                    }).findAny().get())
                            .build();

                    return reservationBuilder;
                }).collect(Collectors.toList());

        reservations.setReservationBuilders(reservationBuilders);
        kafkaProducer.sent(topic, reservations);

        return reservationBuilders;

    }
}
