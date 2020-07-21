package br.com.cvc.builder;

import lombok.Data;

import java.util.List;

@Data
public class Reservations {
    List<ReservationBuilder> reservationBuilders;

}
