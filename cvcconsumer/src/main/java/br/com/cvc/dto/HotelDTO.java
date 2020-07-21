package br.com.cvc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTO implements Serializable {

    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "cityCode")
    private String cityCode;
    @JsonProperty(value = "cityName")
    private String cityName;
    @JsonProperty(value = "rooms")
    private Set<Rooms> rooms;

}
