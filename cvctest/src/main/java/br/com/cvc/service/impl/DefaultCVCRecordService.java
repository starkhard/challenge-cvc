package br.com.cvc.service.impl;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.dto.HotelDTO;
import br.com.cvc.service.CVCConverterKafkaProducer;
import br.com.cvc.service.CVCReactiveRecordService;
import br.com.cvc.service.CVCRecordService;
import br.com.cvc.validator.CVCDatasValidator;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@Slf4j
public class DefaultCVCRecordService {

    @Autowired
    private CVCRecordService cvcRecordService;
    @Autowired
    private CVCReactiveRecordService cvcReactiveRecordService;
    @Autowired
    private CVCConverterKafkaProducer cvcConverterKafkaProducer;
    @Autowired
    private CVCDatasValidator cvcDatasValidator;


    @HystrixCommand(fallbackMethod = "availableFallBackHotelsByCity",
            threadPoolKey = "availableThreadByCityKey",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000")})
    public List<ReservationBuilder> getHotelsAvailableByCity(CVCReservationDTO cvcReservationDTO) {
        Errors errors = new BeanPropertyBindingResult(cvcReservationDTO, "cvcReservationDTO");
        cvcDatasValidator.validate(cvcReservationDTO, errors);
        List<HotelDTO> hotelDTOS = cvcRecordService.getHotelsAvailableByCity(cvcReservationDTO.getId());
        return cvcConverterKafkaProducer.converter(hotelDTOS, cvcReservationDTO);
    }

    @HystrixCommand(fallbackMethod = "availableFallBackHotelsByHotelId",
            threadPoolKey = "availableThreadByCityKey",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000")})
    public List<ReservationBuilder> getDetailsHotelById(CVCReservationDTO cvcReservationDTO) {
        Errors errors = new BeanPropertyBindingResult(cvcReservationDTO, "cvcReservationDTO");
        cvcDatasValidator.validate(cvcReservationDTO, errors);
        List<HotelDTO> hotelDTO = cvcRecordService.getDetailsHotelById(cvcReservationDTO.getId());
        return cvcConverterKafkaProducer.converter(hotelDTO, cvcReservationDTO);
    }

    private List<ReservationBuilder> availableFallBackHotelsByCity(CVCReservationDTO cvcReservationDTO) {
        return cvcReactiveRecordService.getAllHotelsFallback();
    }

    private List<ReservationBuilder> availableFallBackHotelsByHotelId(CVCReservationDTO cvcReservationDTO) {
        return cvcReactiveRecordService.getHotelByIdFallback(cvcReservationDTO.getId());
    }
}
