package br.com.cvc.service;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.service.impl.DefaultCVCRecordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;


@SpringBootTest(properties = "feign.hystrix.enabled=true")
@EnableCircuitBreaker
@TestPropertySource("classpath:application.properties")
public class DefaultCVCRecordServiceTest {

    @Autowired
    private DefaultCVCRecordService defaultCVCRecordService;


    @Test
    public void testFallbackCvcByHotelsByCityAndHotelById() {
        assertThat(this.defaultCVCRecordService.getHotelsAvailableByCity(dto()), notNullValue());
        assertThat(this.defaultCVCRecordService.getHotelsAvailableByCity(dto()),isA(List.class));
        List<ReservationBuilder> reservationsByCityId =  this.defaultCVCRecordService.getHotelsAvailableByCity(dto());
        List<ReservationBuilder> reservationsByHotelId = this.defaultCVCRecordService.getDetailsHotelById(dto2(reservationsByCityId.stream().findFirst().get().getId()));
        assertThat(reservationsByHotelId,notNullValue());
        Assertions.assertEquals(reservationsByCityId.stream().findFirst().get().getId(),reservationsByHotelId.get(0).getId());
    }

    private CVCReservationDTO dto() {
        LocalDate localDate = LocalDate.now();
        CVCReservationDTO cvcReservationDTO = new CVCReservationDTO();
        cvcReservationDTO.setId("1032");
        cvcReservationDTO.setNumberOfChildren(1);
        cvcReservationDTO.setNumberOfAdults(2);
        cvcReservationDTO.setCheckInDate(localDate);
        cvcReservationDTO.setCheckOutDate(localDate.plusDays(4));
        return cvcReservationDTO;
    }

    private CVCReservationDTO dto2(String id) {
        LocalDate localDate = LocalDate.now();
        CVCReservationDTO cvcReservationDTO = new CVCReservationDTO();
        cvcReservationDTO.setId(id);
        cvcReservationDTO.setNumberOfChildren(1);
        cvcReservationDTO.setNumberOfAdults(2);
        cvcReservationDTO.setCheckInDate(localDate);
        cvcReservationDTO.setCheckOutDate(localDate.plusDays(4));
        return cvcReservationDTO;
    }
}

