package me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelImage;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MarvelSeries {
    private int id;
    private String title;
    private String description;
    private String resourceURI;
    private int startYear;
    private int endYear;
    private String rating;
    private MarvelImage thumbnail;
}
