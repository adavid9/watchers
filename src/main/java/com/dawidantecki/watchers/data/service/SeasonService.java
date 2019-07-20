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
        if (season == null)
            return;
        Season s = findByName(season.getName());
        if (s != null) {
            s.setName(season.getName());
            s.setRelease_date(season.getRelease_date());
            s.setSeries(season.getSeries());
            s.setEpisodes(season.getEpisodes());
            seasonRepository.save(s);
        } else {
            seasonRepository.save(season);
        }
    }

    public void deleteSeasonById(long id) {
        Season season = findById(id);
        deleteOperation(season);
    }

    public void deleteSeason(Season season) {
        deleteSeasonById(season.getId());
    }

    public void deleteOperation(Season season) {
        if (season == null)
            return;

        season.getEpisodes().forEach(x -> episodeService.deleteEpisodeById(x.getId()));
        seasonRepository.delete(season);
    }
}
