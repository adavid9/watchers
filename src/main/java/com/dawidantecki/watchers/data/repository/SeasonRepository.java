package com.dawidantecki.watchers.data.repository;

import com.dawidantecki.watchers.data.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
