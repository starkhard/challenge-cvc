package br.com.cvc.kafka;


import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.builder.Reservations;
import br.com.cvc.model.PriceModel;
import br.com.cvc.model.ReservationModel;
import br.com.cvc.model.Rooms;
import br.com.cvc.service.impl.DefaultReservationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class KafkaListener {

    @Autowired
    private DefaultReservationImpl defaultReservation;

    @org.springframework.kafka.annotation.KafkaListener(topics = "${cvc.topic.name}", groupId = "${cvc.topic.name}")
    public void kafkaListener(Reservations reservationBuilders) {

        reservationBuilders.getReservationBuilders().forEach(data ->{

            ReservationModel reservationModel = new ReservationModel();
            reservationModel.setHotelId(data.getId());
            reservationModel.setCity(data.getCity());
            reservationModel.setTotalPrice(data.getTotalPrice());

            PriceModel priceModel = new PriceModel();
            priceModel.setPriceChild(data.getPriceDetails().getPricePerDayChild());
            priceModel.setPriceAdult(data.getPriceDetails().getPricePerDayAdult());
            reservationModel.setPriceDetails(priceModel);

            reservationModel.setRooms(data.getRooms().stream().map(room -> {
                Rooms rooms = new Rooms();
                rooms.setCategoryName(room.getCategory().getName());
                rooms.setRoomID(room.getId());
                return rooms;

            }).collect(Collectors.toSet()));
            defaultReservation.createReservation(reservationModel);
        });
    }
}


