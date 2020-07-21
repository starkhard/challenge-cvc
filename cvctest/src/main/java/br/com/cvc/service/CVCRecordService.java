package br.com.cvc.service;

import br.com.cvc.dto.HotelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "${cvc.api.name}", url = "${cvc.uri.request}")
public interface CVCRecordService {

    @GetMapping("/avail/{id}")
    List<HotelDTO> getHotelsAvailableByCity(@PathVariable String id);


    @GetMapping("/{id}")
    List<HotelDTO> getDetailsHotelById(@PathVariable String id);

}
