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

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public Season findById(long id) {
        return seasonRepository.findById(id).orElse(null);
    }

    public List<Season> findAll() {
        return seasonRepository.findAll();
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
        if (season != null)
            seasonRepository.delete(season);
    }
}
