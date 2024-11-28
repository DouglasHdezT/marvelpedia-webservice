package me.douglashdezt.simanmarvelpediaws.repositories.external;

import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelSeries;
import me.douglashdezt.simanmarvelpediaws.repositories.external.configs.MarvelFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "marvel-series-client", url = "${webservices.marvel}", configuration = MarvelFeignConfiguration.class)
public interface SeriesRepository {
    @GetMapping("/series")
    MarvelResponse<MarvelSeries> getAllSeries(
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    );

    @GetMapping("/series")
    MarvelResponse<MarvelSeries> getSeriesByName(
            @RequestParam(name = "titleStartsWith") String nameStartsWith,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    );

    @GetMapping("/series/{id}")
    MarvelResponse<MarvelSeries> getSeriesById(@PathVariable String id);

    @GetMapping("/series/{id}/characters")
    MarvelResponse<MarvelCharacter> getCharactersBySeries(
            @PathVariable String id,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    );
}