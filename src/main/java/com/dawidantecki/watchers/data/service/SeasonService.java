package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class SeasonService {

    private SeasonRepository seasonRepository;
    private EpisodeService episodeService;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository, EpisodeService episodeService) {
        this.seasonRepository = seasonRepository;
        this.episodeService = episodeService;
    }

    public Season findById(long id) {
        return seasonRepository.findById(id).orElse(null);
    }

    public List<Season> findAll() {
        return seasonRepository.findAll();
    }

    public Season findByName(String name) {
        return seasonRepository.findByName(name);
    }

    public void addSeason(Season season) {
        if (season != null)
            seasonRepository.save(season);
    }

    public void addSeasons(Collection<Season> seasons) {
        if (seasons.size() > 0)
            seasons.forEach(x -> {
                if (x != null)
                    addSeason(x);
            });
    }

    public void deleteSeasonById(long id) {
        Season season = findById(id);
        deleteOperation(season);
    }

    public void deleteSeasonByName(String name) {
        Season season = seasonRepository.findByName(name);
        deleteOperation(season);
    }

    public void deleteSeason(Season season) {
        deleteSeasonById(season.getId());
    }

    public void deleteSeasons(Collection<Season> seasons) {
        if (seasons.size() > 0)
            seasons.forEach(x -> {
                if (x != null)
                    deleteOperation(x);
            });
    }

    public void deleteOperation(Season season) {
        if (season == null)
            return;

        season.getEpisodes().forEach(x -> episodeService.deleteEpisodeById(x.getId()));
        seasonRepository.delete(season);
    }
}
