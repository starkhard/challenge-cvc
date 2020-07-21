package br.com.cvc.builder;

import br.com.cvc.model.ReservationModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
@ToString
public class ReservationBuilder implements Serializable {

    private String id;
    private String city;
    private Set<RoomsBuilder> rooms;
    private BigDecimal totalPrice;
    private PriceBuilder priceDetails;

    public ReservationBuilder(){}

    public ReservationBuilder (ReservationModel reservationModel){
        this.id = reservationModel.getHotelId();
        this.city = reservationModel.getCity();
        this.totalPrice = reservationModel.getTotalPrice();
        this.rooms =
        reservationModel.getRooms().stream().map(r -> {
            RoomsBuilder roomsBuilder = new RoomsBuilder();
            CategoryBuilder categoryBuilder = new CategoryBuilder();
            categoryBuilder.setName(r.getCategoryName());
            roomsBuilder.setCategory(categoryBuilder);
            roomsBuilder.setId(r.getRoomID());
            return  roomsBuilder;
        }).collect(Collectors.toSet());
        this.priceDetails =  new PriceBuilder(reservationModel.getPriceDetails().getPriceAdult(),
                reservationModel.getPriceDetails().getPriceChild());

    }

}
