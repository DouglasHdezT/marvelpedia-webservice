package me.douglashdezt.simanmarvelpediaws.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.repositories.external.CharacterRepository;
import me.douglashdezt.simanmarvelpediaws.services.CharacterService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public MarvelPaginationInfo<MarvelCharacter> findCharactersByName(String name, int limit, int offset) {
        MarvelResponse<MarvelCharacter> response =  characterRepository.getCharactersByName(name, limit, offset);
        return response.getCode() == 200 ? response.getData(): null;
    }

    @Override
    public MarvelPaginationInfo<MarvelCharacter> findCharactersById(int id, int limit, int offset) {
        return null;
    }
}
