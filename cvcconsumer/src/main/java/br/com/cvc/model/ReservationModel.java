package br.com.cvc.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Document(collection = "reservationModel")
public class ReservationModel  {

    @Id
    private String id;
    @Indexed(unique = true)
    private String hotelId;
    private String city;
    private Set<Rooms> rooms;
    private BigDecimal totalPrice;
    private PriceModel priceDetails;

}
