package me.douglashdezt.simanmarvelpediaws.dtos.marvelapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelPaginationInfo<T> {
    private int limit;
    private int offset;
    private int total;
    private int count;
    private List<T> results;
}
