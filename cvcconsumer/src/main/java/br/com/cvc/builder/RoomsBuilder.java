package br.com.cvc.builder;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomsBuilder implements Serializable {

    private String id;
    private CategoryBuilder category;
}
