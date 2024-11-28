package me.douglashdezt.simanmarvelpediaws.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
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
    public MarvelCharacter findCharactersById(String id) {
        try {
            MarvelResponse<MarvelCharacter> response = characterRepository.getCharactersById(id);
            if (response.getCode() == 200) {
                MarvelPaginationInfo<MarvelCharacter> paginationInfo = response.getData();

                if (paginationInfo.getResults() != null && !paginationInfo.getResults().isEmpty()) {
                    return paginationInfo.getResults().get(0);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MarvelPaginationInfo<MarvelComic> findComicsByCharacterId(String id, int limit, int offset) {
        try {
            MarvelResponse<MarvelComic> response = characterRepository.getComicsByCharacter(id, limit, offset);
            return response.getCode() == 200 ? response.getData(): null;
        } catch (Exception e) {
            return null;
        }
    }
}
