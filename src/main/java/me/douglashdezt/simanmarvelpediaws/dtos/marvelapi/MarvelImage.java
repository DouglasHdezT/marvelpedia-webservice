package me.douglashdezt.simanmarvelpediaws.dtos.marvelapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MarvelImage {
    private String path;
    private String extension;
}
