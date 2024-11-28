package me.douglashdezt.simanmarvelpediaws.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelSeries;
import me.douglashdezt.simanmarvelpediaws.repositories.external.ComicRepository;
import me.douglashdezt.simanmarvelpediaws.repositories.external.SeriesRepository;
import me.douglashdezt.simanmarvelpediaws.services.ComicService;
import me.douglashdezt.simanmarvelpediaws.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;

    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public MarvelPaginationInfo<MarvelSeries> findAllSeries(int limit, int offset) {
        try {
            MarvelResponse<MarvelSeries> response =  seriesRepository.getAllSeries(limit, offset);
            return response.getCode() == 200 ? response.getData(): null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public MarvelPaginationInfo<MarvelSeries> findSeriesByName(String name, int limit, int offset) {
        try {
            MarvelResponse<MarvelSeries> response =  seriesRepository.getSeriesByName(name, limit, offset);
            return response.getCode() == 200 ? response.getData(): null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public MarvelSeries findSeriesById(String id) {
        try {
            MarvelResponse<MarvelSeries> response = seriesRepository.getSeriesById(id);
            if (response.getCode() == 200) {
                MarvelPaginationInfo<MarvelSeries> paginationInfo = response.getData();

                if (paginationInfo.getResults() != null && !paginationInfo.getResults().isEmpty()) {
                    return paginationInfo.getResults().get(0);
                }
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public MarvelPaginationInfo<MarvelCharacter> findCharactersBySeriesId(String id, int limit, int offset) {
        try {
            MarvelResponse<MarvelCharacter> response =  seriesRepository.getCharactersBySeries(id, limit, offset);
            return response.getCode() == 200 ? response.getData(): null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


}
