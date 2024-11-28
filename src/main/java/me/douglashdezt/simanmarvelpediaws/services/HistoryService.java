package me.douglashdezt.simanmarvelpediaws.services;

import me.douglashdezt.simanmarvelpediaws.models.SearchHistory;
import me.douglashdezt.simanmarvelpediaws.models.User;

import java.util.List;

public interface HistoryService {
    void save(User user, String model, String type, String search, int limit, int offset);

    List<SearchHistory> findAllByUser(User user);
    List<SearchHistory> findAllByModel(String model);
}
