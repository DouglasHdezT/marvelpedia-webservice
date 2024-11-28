package me.douglashdezt.simanmarvelpediaws.dtos.marvelapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelResponse<T> {
    private int code;
    private String status;
    private MarvelPaginationInfo<T> data;
}
