package br.com.cvc.service;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.dto.HotelDTO;

import java.util.List;

public interface CVCConverterKafkaProducer {

    List<ReservationBuilder> converter(List<HotelDTO> hotelDTOS, CVCReservationDTO cvcReservationDTO);
}
