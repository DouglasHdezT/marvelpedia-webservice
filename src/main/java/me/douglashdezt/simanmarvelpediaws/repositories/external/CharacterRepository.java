package me.douglashdezt.simanmarvelpediaws.repositories.external;

import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.repositories.external.configs.MarvelFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "marvel-character-client", url = "${webservices.marvel}", configuration = MarvelFeignConfiguration.class)
public interface CharacterRepository {
    @GetMapping("/characters")
    MarvelResponse<MarvelCharacter> getCharactersByName(
            @RequestParam(name = "nameStartsWith") String nameStartsWith,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    );

    @GetMapping("/characters/{id}")
    MarvelResponse<MarvelCharacter> getCharactersById(@PathVariable String id);

    @GetMapping("/characters/{id}/comics")
    MarvelResponse<MarvelComic> getComicsByCharacter(
            @PathVariable String id,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    );
}
