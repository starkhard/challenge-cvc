package br.com.cvc.builder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        creatorVisibility = JsonAutoDetect.Visibility.ANY
)
public class RoomsBuilder implements Serializable {

    private String id;
    private CategoryBuilder category;
}
