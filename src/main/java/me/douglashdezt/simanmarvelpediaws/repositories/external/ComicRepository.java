package me.douglashdezt.simanmarvelpediaws.repositories.external;

import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.repositories.external.configs.MarvelFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "marvel-comics-client", url = "${webservices.marvel}", configuration = MarvelFeignConfiguration.class)
public interface ComicRepository {
    @GetMapping("/comics")
    MarvelResponse<MarvelComic> getAllComics(
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    );

    @GetMapping("/comics")
    MarvelResponse<MarvelComic> getComicsByName(
            @RequestParam(name = "titleStartsWith") String nameStartsWith,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    );

    @GetMapping("/comics/{id}")
    MarvelResponse<MarvelComic> getComicsById(@PathVariable String id);

    @GetMapping("/comics/{id}/characters")
    MarvelResponse<MarvelCharacter> getCharactersByComic(
            @PathVariable String id,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    );
}