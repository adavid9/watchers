package com.dawidantecki.watchers.data.repository;

import com.dawidantecki.watchers.data.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
