package br.com.cvc.http;


import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.service.impl.DefaultCVCRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel/v1/consult/")
public class HotelController {

    @Autowired
    private DefaultCVCRecordService defaultCVCRecordService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("findAllHotelsByCityId")
    @Cacheable(value = "hotelsByCity")
    public List<ReservationBuilder> checkReservationsForAllHotelsByCity(@RequestBody CVCReservationDTO cvcReservationDTO) {
        return defaultCVCRecordService.getHotelsAvailableByCity(cvcReservationDTO);
    }

    @PostMapping("findHotelById")
    @Cacheable(value = "hotelsById")
    public List<ReservationBuilder> checkReservationForSpecificHotelId(@RequestBody CVCReservationDTO cvcReservationDTO) {
        return defaultCVCRecordService.getDetailsHotelById(cvcReservationDTO);
    }
}
