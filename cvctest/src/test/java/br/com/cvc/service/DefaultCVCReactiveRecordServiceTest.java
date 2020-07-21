package br.com.cvc.service;


import br.com.cvc.builder.ReservationBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import java.util.List;

@SpringBootTest
public class DefaultCVCReactiveRecordServiceTest {

    @Autowired
    private CVCReactiveRecordService cvcReactiveRecordService;
    private static final String HOTEL_ID = "6";

    @Test
    public void testRealCallAllInformationWebFluxFallback(){
        List<ReservationBuilder> reservationBuilders = cvcReactiveRecordService.getAllHotelsFallback();
        Assertions.assertTrue(!CollectionUtils.isEmpty(reservationBuilders));
        Assertions.assertTrue(reservationBuilders.stream().findAny().isPresent());
    }

    @Test
    public void testRealCallInformationByHotelIdWebfluxFallback(){
        List<ReservationBuilder> reservationBuilder = cvcReactiveRecordService.getHotelByIdFallback(HOTEL_ID);
        Assertions.assertNotNull(reservationBuilder);
    }

}
