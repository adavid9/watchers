package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class SeriesService {

    private SeriesRepository seriesRepository;
    private SeasonService seasonService;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository, SeasonService seasonService) {
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
        if (series != null)
            seriesRepository.save(series);
    }

    public void addSeries(Collection<Series> series) {
        if (series.size() > 0)
            series.forEach(x -> {
                if (x != null)
                    addSeries(x);
            });
    }

    public void deleteSeries(long id) {
        Series series = findById(id);
        deleteOperation(series);
    }

    public void deleteSeries(String title) {
        Series series = findByTitle(title);
        deleteOperation(series);
    }

    private void deleteOperation(Series series) {
        if (series == null)
            return;

        series.getSeasons().forEach(x -> seasonService.deleteSeasonById(x.getId()));
        seriesRepository.delete(series);
    }
}
