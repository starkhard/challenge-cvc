package br.com.cvc.service;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.model.ReservationModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReservationService {

    Flux<ReservationBuilder> getHotelById(String id);

    void createReservation(ReservationModel data);

    Mono<List<ReservationBuilder>> getAllHotels();

    Flux<ReservationBuilder> getAllHotelsByCityId(String id);


}
