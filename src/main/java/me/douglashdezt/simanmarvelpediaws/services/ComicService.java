package me.douglashdezt.simanmarvelpediaws.services;

import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;

public interface ComicService {
    MarvelPaginationInfo<MarvelComic> findAllComics(int limit, int offset);
    MarvelPaginationInfo<MarvelComic> findComicsByName(String name, int limit, int offset);
    MarvelComic findComicById(String id);

    MarvelPaginationInfo<MarvelCharacter> findCharactersByComicId(String id, int limit, int offset);
}
