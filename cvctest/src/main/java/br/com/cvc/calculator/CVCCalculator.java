package br.com.cvc.calculator;

import br.com.cvc.builder.ReservationBuilder;
import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.dto.HotelDTO;

import java.math.BigDecimal;

public interface CVCCalculator {

    BigDecimal calculateTaxByPerson(HotelDTO response, CVCReservationDTO cvcReservationDTO);

    BigDecimal calculateReverseTaxByPerson(ReservationBuilder response, CVCReservationDTO cvcReservationDTO);



}
