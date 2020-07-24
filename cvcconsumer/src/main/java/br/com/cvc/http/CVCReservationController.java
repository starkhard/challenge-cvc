package br.com.cvc.http;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.service.impl.DefaultReservationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recovery/v1/hotels/")
public class CVCReservationController {

    @Autowired
    private DefaultReservationImpl defaultReservation;

    @GetMapping("/allHotels")
    public Mono<List<ReservationBuilder>> findAllHotels(){
        return defaultReservation.getAllHotels();
    }

    @GetMapping("/allHotelsByCityId/{id}")
    public Flux<ReservationBuilder> findAllHotelsByCity(@PathVariable String id){
        return defaultReservation.getAllHotelsByCityId(id);
    }

    @GetMapping("/hotelId/{id}")
    public Flux<ReservationBuilder> findHotelById(@PathVariable String id){
        return defaultReservation.getHotelById(id);
    }
}
