package me.douglashdezt.simanmarvelpediaws.controllers;

import me.douglashdezt.simanmarvelpediaws.dtos.GeneralResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelSeries;
import me.douglashdezt.simanmarvelpediaws.services.ComicService;
import me.douglashdezt.simanmarvelpediaws.services.HistoryService;
import me.douglashdezt.simanmarvelpediaws.services.SeriesService;
import me.douglashdezt.simanmarvelpediaws.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data/series")
public class SeriesController {
    private final SeriesService seriesService;
    private final HistoryService historyService;
    private final UserService userService;

    public SeriesController(SeriesService seriesService, HistoryService historyService, UserService userService) {
        this.seriesService = seriesService;
        this.historyService = historyService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> findAllSeries(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                                         @RequestParam(value = "offset", defaultValue = "0") int offset) {
        MarvelPaginationInfo<MarvelSeries> series = seriesService.findAllSeries(limit, offset);

        if(series == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Series not found");
        }

        historyService.save(
                userService.findAuthenticated(), "series", "all", "", limit, offset
        );

        return GeneralResponse.getResponse("Series found", series);
    }

    @GetMapping("/by-name")
    public ResponseEntity<GeneralResponse> findSeriesByName(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset
    ) {
        MarvelPaginationInfo<MarvelSeries> series = seriesService.findSeriesByName(name, limit, offset);

        if(series == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Series not found");
        }

        historyService.save(
                userService.findAuthenticated(), "series", "by-name", name, limit, offset
        );

        return GeneralResponse.getResponse("Series found", series);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<GeneralResponse> findSeriesById(@PathVariable String id) {
        MarvelSeries series = seriesService.findSeriesById(id);

        if(series == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Series not found");
        }

        historyService.save(
                userService.findAuthenticated(), "series", "by-id", id, 0, 0
        );

        return GeneralResponse.getResponse("Series found", series);
    }

    @GetMapping("/{id}/characters")
    public ResponseEntity<GeneralResponse> findCharactersBySeries(@PathVariable String id,
         @RequestParam(value = "limit", defaultValue = "10") int limit,
         @RequestParam(value = "offset", defaultValue = "0") int offset
    ) {
        MarvelPaginationInfo<MarvelCharacter> characters = seriesService.findCharactersBySeriesId(id, limit, offset);

        if(characters == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Characters not found");
        }

        historyService.save(
                userService.findAuthenticated(), "characters", "by-series", id, limit, offset
        );

        return GeneralResponse.getResponse("Characters found", characters);
    }
}
