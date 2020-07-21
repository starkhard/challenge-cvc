package br.com.cvc.builder;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PriceBuilder implements Serializable {

    private BigDecimal pricePerDayAdult;
    private BigDecimal pricePerDayChild;

    PriceBuilder() {
    }

    PriceBuilder(BigDecimal adult, BigDecimal child) {
        this.pricePerDayAdult = adult;
        this.pricePerDayChild = child;
    }
}
