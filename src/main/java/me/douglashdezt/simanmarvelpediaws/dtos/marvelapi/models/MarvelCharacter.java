package me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelImage;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MarvelCharacter {
    private int id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private MarvelImage thumbnail;
}
