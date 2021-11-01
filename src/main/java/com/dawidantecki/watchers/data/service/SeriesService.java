package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeasonService seasonService;

    @Autowired
    public SeriesService(final SeriesRepository seriesRepository, final SeasonService seasonService) {
        this.seriesRepository = seriesRepository;
        this.seasonService = seasonService;
    }

    public Series findById(long id) {
        return seriesRepository.findById(id).orElse(null);
    }

    public List<Series> findAll() {
        return seriesRepository.findAll();
    }

    public Series findByTitle(String title) {
        return seriesRepository.findByTitle(title);
    }

    public void addSeries(Series series) {
        if (series == null)
            return;
        Series s = findByTitle(series.getTitle());
        if (s != null) {
            s.setTitle(series.getTitle());
            s.setDescription(series.getDescription());
            s.setCountry(series.getCountry());
            s.setDirector(series.getDirector());
            s.setRelease_date(series.getRelease_date());
            s.setSeasons(series.getSeasons());
            seriesRepository.save(s);
        } else {
            seriesRepository.save(series);
        }
    }

    public void deleteSeries(Series series) {
        deleteOperation(series);
    }

    private void deleteOperation(Series series) {
        if (series == null)
            return;

        series.getSeasons().forEach(seasonService::deleteSeason);
        seriesRepository.delete(series);
    }
}
