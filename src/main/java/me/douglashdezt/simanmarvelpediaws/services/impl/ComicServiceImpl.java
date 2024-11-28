package me.douglashdezt.simanmarvelpediaws.services.impl;

import lombok.extern.slf4j.Slf4j;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelPaginationInfo;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.MarvelResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelCharacter;
import me.douglashdezt.simanmarvelpediaws.dtos.marvelapi.models.MarvelComic;
import me.douglashdezt.simanmarvelpediaws.repositories.external.ComicRepository;
import me.douglashdezt.simanmarvelpediaws.services.ComicService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ComicServiceImpl implements ComicService {
    private final ComicRepository comicRepository;

    public ComicServiceImpl(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    @Override
    public MarvelPaginationInfo<MarvelComic> findComicsByName(String name, int limit, int offset) {
        try {
            MarvelResponse<MarvelComic> response =  comicRepository.getComicsByName(name, limit, offset);
            return response.getCode() == 200 ? response.getData(): null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MarvelComic findComicById(String id) {
        try {
            MarvelResponse<MarvelComic> response = comicRepository.getComicsById(id);
            if (response.getCode() == 200) {
                MarvelPaginationInfo<MarvelComic> paginationInfo = response.getData();

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
    public MarvelPaginationInfo<MarvelCharacter> findCharactersByComicId(String id, int limit, int offset) {
        try {
            MarvelResponse<MarvelCharacter> response =  comicRepository.getCharactersByComic(id, limit, offset);
            return response.getCode() == 200 ? response.getData(): null;
        } catch (Exception e) {
            return null;
        }
    }
}
