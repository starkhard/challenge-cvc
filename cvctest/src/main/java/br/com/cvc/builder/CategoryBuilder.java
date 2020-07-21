package br.com.cvc.builder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        creatorVisibility = JsonAutoDetect.Visibility.ANY
)
public class CategoryBuilder {

    private String name;
}
