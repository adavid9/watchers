package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeriesService {

    private SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public Series findById(long id) {
        return seriesRepository.findById(id).orElse(null);
    }

    public List<Series> findAll() {
        return seriesRepository.findAll();
    }

    public void addSeries(Series series) {
        if (series != null)
            seriesRepository.save(series);
    }

    public void deleteSeries(long id) {
        Series series = findById(id);
        if (series != null)
            seriesRepository.delete(series);
    }
}
