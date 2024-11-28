package me.douglashdezt.simanmarvelpediaws.controllers;

import me.douglashdezt.simanmarvelpediaws.dtos.GeneralResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.services.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/by-name")
    public ResponseEntity<GeneralResponse> findCharactersByName(
            @RequestParam("name") String name,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset
        ) {
        MarvelPaginationInfo<MarvelCharacter> characters = characterService.findCharactersByName(name, limit, offset);

        if(characters == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Characters not found");
        }

        return GeneralResponse.getResponse(HttpStatus.OK, "Characters found", characters);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<GeneralResponse> findCharactersById(@PathVariable String id) {
        MarvelCharacter character = characterService.findCharactersById(id);

        if(character == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Character not found");
        }

        return GeneralResponse.getResponse(HttpStatus.OK, "Character found", character);
    }

    @GetMapping("/{id}/comics")
    public ResponseEntity<GeneralResponse> findComicsByCharacterId(@PathVariable String id,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset
    ) {
        MarvelPaginationInfo<MarvelComic> comics = characterService.findComicsByCharacterId(id, limit, offset);

        if(comics  == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Comics not found");
        }

        return GeneralResponse.getResponse(HttpStatus.OK, "Comics found", comics);
    }
}
