package br.com.cvc.builder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        creatorVisibility = JsonAutoDetect.Visibility.ANY
)
public class PriceBuilder implements Serializable {

    private BigDecimal pricePerDayAdult;
    private BigDecimal pricePerDayChild;
}
