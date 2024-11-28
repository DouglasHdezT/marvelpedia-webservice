package me.douglashdezt.simanmarvelpediaws.controllers;

import me.douglashdezt.simanmarvelpediaws.dtos.GeneralResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.services.CharacterService;
import me.douglashdezt.simanmarvelpediaws.services.HistoryService;
import me.douglashdezt.simanmarvelpediaws.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data/characters")
public class CharacterController {
    private final CharacterService characterService;
    private final HistoryService historyService;
    private final UserService userService;

    public CharacterController(CharacterService characterService, HistoryService historyService, UserService userService) {
        this.characterService = characterService;
        this.historyService = historyService;
        this.userService = userService;
    }

    @GetMapping("/by-name")
    public ResponseEntity<GeneralResponse> findCharactersByName(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset
        ) {
        MarvelPaginationInfo<MarvelCharacter> characters = characterService.findCharactersByName(name, limit, offset);

        if(characters == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Characters not found");
        }

        historyService.save(
                userService.findAuthenticated(), "characters", "by-name", name, limit, offset
        );
        return GeneralResponse.getResponse(HttpStatus.OK, "Characters found", characters);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<GeneralResponse> findCharactersById(@PathVariable String id) {
        MarvelCharacter character = characterService.findCharactersById(id);

        if(character == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Character not found");
        }

        historyService.save(
                userService.findAuthenticated(), "characters", "by-id", id, 0, 0
        );
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

        historyService.save(
                userService.findAuthenticated(), "comics", "by-character", id, limit, offset
        );
        return GeneralResponse.getResponse(HttpStatus.OK, "Comics found", comics);
    }
}
