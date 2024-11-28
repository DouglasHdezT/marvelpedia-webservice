package me.douglashdezt.simanmarvelpediaws.services;

import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;

public interface CharacterService {
    MarvelPaginationInfo<MarvelCharacter> findCharactersByName(String name, int limit, int offset);
    MarvelPaginationInfo<MarvelCharacter> findCharactersById(int id, int limit, int offset);
}