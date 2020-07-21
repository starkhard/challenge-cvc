package br.com.cvc.builder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        creatorVisibility = JsonAutoDetect.Visibility.ANY
)
@EqualsAndHashCode
@ToString
public class ReservationBuilder implements Serializable {

    private String id;
    private String city;
    private Set<RoomsBuilder> rooms;
    private BigDecimal totalPrice;
    private PriceBuilder priceDetails;


}
