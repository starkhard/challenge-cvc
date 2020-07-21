package br.com.cvc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rooms  implements Serializable {

    @JsonProperty("roomID")
    private String  roomID;
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("price")
    private PriceDTO price;

}
