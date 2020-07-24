package br.com.cvc.service.impl;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.model.ReservationModel;
import br.com.cvc.repository.CVCRepository;
import br.com.cvc.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
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

    @Value("${duration.one}")
    private Long durationCalledOne;
    @Value("${duration.two}")
    private Long durationCalledTwo;
    @Value("${duration.three}")
    private Long durationCalledThree;

    @Override
    public Flux<ReservationBuilder> getHotelById(String id) {

        return cvcRepository.findByHotelId(id).map(r -> {
            ReservationBuilder reservationBuilder = new ReservationBuilder(r);
            return reservationBuilder;
        }).log().cache().take(durationCalledOne);
    }

    @Override
    public void createReservation(ReservationModel data) {
        try {
            cvcRepository.save(data).subscribe();
        } catch (Exception cause) {
            log.error("problem to save [cvc-01]" + cause);
        }
    }

    @Override
    public Mono<List<ReservationBuilder>> getAllHotels() {
        return cvcRepository.findAll().map(r -> {
            ReservationBuilder reservationBuilder = new ReservationBuilder(r);
            return reservationBuilder;
        }).collectList().log().cache().take(Duration.ofSeconds(durationCalledTwo));
    }

    @Override
    public Flux<ReservationBuilder> getAllHotelsByCityId(String code) {
        return cvcRepository.findHotelByCity(code).log().map(r -> {
            ReservationBuilder reservationBuilder = new ReservationBuilder(r);
            return reservationBuilder;
        }).cache().take(durationCalledThree );
    }
}
