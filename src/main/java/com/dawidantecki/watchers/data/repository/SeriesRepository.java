package com.dawidantecki.watchers.data.repository;

import com.dawidantecki.watchers.data.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
