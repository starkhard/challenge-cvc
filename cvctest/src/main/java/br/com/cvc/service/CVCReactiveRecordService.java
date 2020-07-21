package br.com.cvc.service;

import br.com.cvc.builder.ReservationBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "${cvc.api.reactive.name}", url = "${cvc.uri.reactive}")
public interface CVCReactiveRecordService {

    @GetMapping("/all")
    List<ReservationBuilder> getAllHotelsFallback();

    @GetMapping("/hotelId/{id}")
    List<ReservationBuilder> getHotelByIdFallback(@PathVariable String id);
}
