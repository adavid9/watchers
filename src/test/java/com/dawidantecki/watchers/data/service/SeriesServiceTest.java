package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest;
import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.repository.SeasonRepository;
import com.dawidantecki.watchers.data.repository.SeriesRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SeriesServiceTest extends DatabaseConnectionTest {

    @Autowired
    private SeriesService seriesService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private SeasonRepository seasonRepository;

    private ArrayList<Series> series;

    @Before
    public void setUp() {
        series = seriesList();
    }

    @Test
    public void should_save_series() {
        Series s = series.get(0);
        seriesService.addSeries(s);
        assertEquals(s, seriesService.findById(s.getId()));
    }

    @Test
    public void should_find_series_by_id() {
        Series s = series.get(0);
        seriesService.addSeries(s);
        assertEquals(s, seriesService.findById(s.getId()));
    }

    @Test
    public void should_find_series_by_title() {
        Series s = series.get(0);
        seriesService.addSeries(s);
        assertEquals(s, seriesService.findByTitle(s.getTitle()));
    }

    @Test
    public void should_find_collection_of_series() {
        series.forEach(series -> seriesService.addSeries(series));
        assertEquals(series.size(), seriesService.findAll().size());
    }

    @Test
    public void should_delete_series() {
        Series s = series.get(0);
        seriesService.addSeries(s);
        seriesService.deleteSeries(s);
        assertNull(seriesService.findById(s.getId()));
    }

    @Test
    public void should_delete_series_with_seasons() {
        Series series = seriesWithSeasons();
        series.getSeasons().forEach(season -> seasonService.addSeason(season));
        seriesService.addSeries(series);
        seriesService.deleteSeries(series);

        seasonService.findAll().forEach(Assert::assertNull);
        assertNull(seriesService.findById(series.getId()));
    }

    @Test
    public void should_be_only_one() {
        series.add(new Series("Mr Robot", "", "", ""));
        series.add(new Series("Mr Robot", "", "", ""));

        series.forEach(s -> seriesService.addSeries(s));
        List<Series> seriesList = seriesService.findAll();
        for (Series s1 : seriesList) {
            int sameSeries = 0;
            for (Series s2 : seriesList) {
                if (s1.getTitle().equals(s2.getTitle())) sameSeries++;
                if (sameSeries > 1) fail("Series with already existing title cannot be saved");
            }
        }
    }

    @After
    public void cleanUp() {
        seriesRepository.deleteAll();
        seasonRepository.deleteAll();
    }

    private ArrayList<Series> seriesList() {
        ArrayList<Series> series = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            series.add(new Series("Title" + i, "", "", ""));
        }

        return series;
    }

    private Series seriesWithSeasons() {
        Series series = new Series("Title", "", "", "");
        series.getSeasons().add(new Season("Season"));

        return series;
    }
}