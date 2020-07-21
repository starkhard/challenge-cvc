package br.com.cvc.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "priceModel")
@Getter
@Setter
public class PriceModel {

    @Id
    private long id;
    private BigDecimal priceAdult;
    private BigDecimal priceChild;
}
