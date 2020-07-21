package br.com.cvc.repository;

import br.com.cvc.model.ReservationModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CVCRepository extends ReactiveMongoRepository<ReservationModel, String> {

    @Query("{'hotelId': ?0}")
    Flux<ReservationModel> findByHotelId(String hotelId);
}
