package br.com.cvc.calculator;

import br.com.cvc.dto.CVCReservationDTO;
import br.com.cvc.dto.HotelDTO;

import java.math.BigDecimal;

public interface CVCCalculator {

    BigDecimal calculateTaxByPerson(HotelDTO response, CVCReservationDTO cvcReservationDTO);
}
