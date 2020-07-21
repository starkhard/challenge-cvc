package br.com.cvc.builder;

import java.util.List;

public class Reservations {

    List<ReservationBuilder> reservationBuilders;

    public List<ReservationBuilder> getReservationBuilders() {
        return reservationBuilders;
    }

    public void setReservationBuilders(List<ReservationBuilder> reservationBuilders) {
        this.reservationBuilders = reservationBuilders;
    }
}
