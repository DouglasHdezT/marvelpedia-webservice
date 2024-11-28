package me.douglashdezt.simanmarvelpediaws.services;

import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelSeries;

public interface SeriesService {
    MarvelPaginationInfo<MarvelSeries> findAllSeries(int limit, int offset);
    MarvelPaginationInfo<MarvelSeries> findSeriesByName(String name, int limit, int offset);
    MarvelSeries findSeriesById(String id);

    MarvelPaginationInfo<MarvelCharacter> findCharactersBySeriesId(String id, int limit, int offset);
}
