package me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelImage;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MarvelComic {
    private int id;
    private int digitalId;
    private String title;
    private String description;
    private String isbn;
    private String upc;
    private String ean;
    private String format;
    private int pageCount;
    private String resourceURI;
    private MarvelImage thumbnail;
}