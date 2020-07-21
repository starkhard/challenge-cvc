package br.com.cvc.validator;

import br.com.cvc.dto.CVCReservationDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CVCDatasValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getClass().isInstance(CVCReservationDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (supports(o.getClass())) {

            CVCReservationDTO cvcReservationDTO = (CVCReservationDTO) o;

            if (StringUtils.isBlank(cvcReservationDTO.getId())) {
                throw new RuntimeException("you need put a ID ");
            }

            if (cvcReservationDTO.getNumberOfChildren() == null || cvcReservationDTO.getNumberOfChildren() < 0) {
                throw new RuntimeException("Invalid number of children !");
            }

            if (cvcReservationDTO.getNumberOfAdults() == null || cvcReservationDTO.getNumberOfAdults() < 0) {
                throw new RuntimeException("Invalid number of adults  !");
            }

            if (cvcReservationDTO.getCheckOutDate() == null || cvcReservationDTO.getCheckInDate() == null) {
                throw new RuntimeException("Dates cannot be null   !");

            }
            if (cvcReservationDTO.getCheckOutDate().isBefore(cvcReservationDTO.getCheckInDate())) {
                throw new RuntimeException("Invalid  Checkout reservation date   !");
            }

            if (cvcReservationDTO.getCheckInDate().isAfter(cvcReservationDTO.getCheckOutDate())) {
                throw new RuntimeException("Invalid  Check reservation date   !");
            }

        }

    }
}
