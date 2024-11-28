package me.douglashdezt.simanmarvelpediaws.controllers;

import me.douglashdezt.simanmarvelpediaws.dtos.GeneralResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.services.ComicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data/comics")
public class ComicController {
    private final ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> findAllComics(@RequestParam(value = "limit", defaultValue = "10") int limit,
                                                         @RequestParam(value = "offset", defaultValue = "0") int offset) {
        MarvelPaginationInfo<MarvelComic> comics = comicService.findAllComics(limit, offset);

        if(comics == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Comics not found");
        }

        return GeneralResponse.getResponse("Comics found", comics);
    }

    @GetMapping("/by-name")
    public ResponseEntity<GeneralResponse> findComicsByName(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset
    ) {
        MarvelPaginationInfo<MarvelComic> comics = comicService.findComicsByName(name, limit, offset);

        if(comics == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Comics not found");
        }

        return GeneralResponse.getResponse("Comics found", comics);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<GeneralResponse> findComicById(@PathVariable String id) {
        MarvelComic comic = comicService.findComicById(id);

        if(comic == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Comic not found");
        }

        return GeneralResponse.getResponse("Comic found", comic);
    }

    @GetMapping("/{id}/characters")
    public ResponseEntity<GeneralResponse> findCharactersByComic(@PathVariable String id,
         @RequestParam(value = "limit", defaultValue = "10") int limit,
         @RequestParam(value = "offset", defaultValue = "0") int offset
    ) {
        MarvelPaginationInfo<MarvelCharacter> characters = comicService.findCharactersByComicId(id, limit, offset);

        if(characters == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Characters not found");
        }

        return GeneralResponse.getResponse("Characters found", characters);
    }
}
