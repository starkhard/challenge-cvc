package br.com.cvc.calculator.impl;

import br.com.cvc.calculator.CVCCalculator;
import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.dto.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;


@Service
public class DefaultCVCCalculator implements CVCCalculator {

    @Autowired
    private AdultCalculator adultCalculator;
    @Autowired
    private ChildCalculator childCalculator;

    @Override
    public BigDecimal calculateTaxByPerson(HotelDTO hotelDTO, CVCReservationDTO cvcReservationDTO) {
        double basePriceAdult = hotelDTO.getRooms().stream().map(a -> a.getPrice().getPriceAdult()).findAny().get().doubleValue();
        double basePriceChild = hotelDTO.getRooms().stream().map(a -> a.getPrice().getPriceChild()).findAny().get().doubleValue();
        long days = ChronoUnit.DAYS.between(cvcReservationDTO.getCheckInDate(), cvcReservationDTO.getCheckOutDate());
        return adultCalculator.calc(days, basePriceAdult, cvcReservationDTO.getNumberOfAdults()).add(
                childCalculator.calc(days, basePriceChild, cvcReservationDTO.getNumberOfChildren())).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
