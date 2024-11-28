package me.douglashdezt.simanmarvelpediaws.services;

import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;

public interface CharacterService {
    MarvelPaginationInfo<MarvelCharacter> findCharactersByName(String name, int limit, int offset);
    MarvelCharacter findCharactersById(String id);

    MarvelPaginationInfo<MarvelComic> findComicsByCharacterId(String id, int limit, int offset);
}