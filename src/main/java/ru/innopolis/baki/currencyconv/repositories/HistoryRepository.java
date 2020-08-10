package ru.innopolis.baki.currencyconv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.baki.currencyconv.models.History;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUserID(Long userID);
}