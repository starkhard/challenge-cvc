package br.com.cvc.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roomsModel")
@Getter
@Setter
public class Rooms {

    @Id
    private long id;
    private String  roomID;
    private String categoryName;
}
