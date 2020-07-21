package br.com.cvc.service.impl;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.model.ReservationModel;
import br.com.cvc.repository.CVCRepository;
import br.com.cvc.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class DefaultReservationImpl implements ReservationService {

    @Autowired
    private CVCRepository cvcRepository;
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<ReservationBuilder> findById(String id) {

        return cvcRepository.findByHotelId(id).map(r -> {
            ReservationBuilder reservationBuilder = new ReservationBuilder(r);
            return reservationBuilder;
        }).take(Duration.ofSeconds(15000));
    }

    @Override
    public void createReservation(ReservationModel data) {
        try {
            cvcRepository.save(data).subscribe();
        } catch (Exception cause) {
            log.error("problem to save " + cause);
        }
    }

    @Override
    public Mono<List<ReservationBuilder>> getAllHotels() {
        return cvcRepository.findAll().map(r -> {
            ReservationBuilder reservationBuilder = new ReservationBuilder(r);
            return reservationBuilder;
        }).collectList().take(Duration.ofSeconds(15000));
    }
}
