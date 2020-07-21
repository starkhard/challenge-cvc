package br.com.cvc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceDTO implements Serializable {

    @JsonProperty(value = "adult")
    private BigDecimal priceAdult;
    @JsonProperty(value = "child")
    private BigDecimal priceChild;
}
