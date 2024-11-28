package me.douglashdezt.simanmarvelpediaws.services.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import me.douglashdezt.simanmarvelpediaws.models.SearchHistory;
import me.douglashdezt.simanmarvelpediaws.models.User;
import me.douglashdezt.simanmarvelpediaws.repositories.SearchHistoryRepository;
import me.douglashdezt.simanmarvelpediaws.services.HistoryService;
import me.douglashdezt.simanmarvelpediaws.services.UserService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class HistoryServiceImpl implements HistoryService {
    private final SearchHistoryRepository searchHistoryRepository;

    public HistoryServiceImpl(SearchHistoryRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(User user, String model, String type, String search, int limit, int offset) {
        SearchHistory searchHistory = SearchHistory.builder()
                .user(user)
                .model(model.toLowerCase())
                .type(type.toLowerCase())
                .search(search)
                .limit(limit)
                .offset(offset)
                .timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date.from(Instant.now())))
                .build();

        searchHistoryRepository.save(searchHistory);
    }

    @Override
    public List<SearchHistory> findAllByUser(User user) {
        return searchHistoryRepository.findByUser(user);
    }

    @Override
    public List<SearchHistory> findAllByModel(String model) {
        return searchHistoryRepository.findByModel(model.toLowerCase());
    }
}
