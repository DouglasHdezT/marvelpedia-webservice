package me.douglashdezt.simanmarvelpediaws.repositories;

import me.douglashdezt.simanmarvelpediaws.models.SearchHistory;
import me.douglashdezt.simanmarvelpediaws.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, String > {
    List<SearchHistory> findByUser(User user);
    List<SearchHistory> findByModel(String model);
}
