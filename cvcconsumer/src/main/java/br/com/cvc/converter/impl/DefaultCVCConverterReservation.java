package br.com.cvc.converter.impl;

import br.com.cvc.builder.Reservations;
import br.com.cvc.converter.CVCConveterReservation;
import br.com.cvc.model.PriceModel;
import br.com.cvc.model.ReservationModel;
import br.com.cvc.model.Rooms;
import br.com.cvc.service.impl.DefaultReservationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DefaultCVCConverterReservation implements CVCConveterReservation {

    @Autowired
    private DefaultReservationImpl defaultReservation;

    @Override
    public void converter(Reservations reservations) {

        reservations.getReservationBuilders().forEach(data -> {

            ReservationModel reservationModel = new ReservationModel();
            reservationModel.setHotelId(data.getId());
            reservationModel.setCity(data.getCity());
            reservationModel.setCityCode(data.getCityCode());
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
