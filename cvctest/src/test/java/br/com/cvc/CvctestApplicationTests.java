package br.com.cvc;

import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.service.impl.DefaultCVCRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class CvctestApplicationTests {

    private static final String codes[] = {"1032", "7110", "9626"};
    @Autowired
    private DefaultCVCRecordService defaultCVCRecordService;

    @Test
    void contextLoads() {
        Arrays.stream(codes).forEach(code -> {
            defaultCVCRecordService.getHotelsAvailableByCity(datas(code));
        });
    }


    private CVCReservationDTO datas(String code) {
        LocalDate localDate = LocalDate.now();
        CVCReservationDTO cvcReservationDTO = new CVCReservationDTO();
        cvcReservationDTO.setId(code);
        cvcReservationDTO.setNumberOfChildren(1);
        cvcReservationDTO.setNumberOfAdults(2);
        cvcReservationDTO.setCheckInDate(localDate);
        cvcReservationDTO.setCheckOutDate(localDate.plusDays(4));
        return cvcReservationDTO;
    }
}
