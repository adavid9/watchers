package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest;
import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.repository.EpisodeRepository;
import com.dawidantecki.watchers.data.repository.SeasonRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SeasonServiceTest extends DatabaseConnectionTest {

    @Autowired
    private SeasonService seasonService;
    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private EpisodeRepository episodeRepository;

    private ArrayList<Season> seasons;

    @Before
    public void setUp() {
        seasons = seasonsList();
    }

    @Test
    public void should_save_season() {
        Season season = seasons.get(0);
        seasonService.addSeason(season);
        assertEquals(season, seasonService.findById(season.getId()));
    }

    @Test
    public void should_find_season_by_name() {
        Season season = seasons.get(0);
        seasonService.addSeason(season);
        assertEquals(season, seasonService.findByName(season.getName()));
    }

    @Test
    public void should_find_all_seasons() {
        seasons.forEach(season -> seasonService.addSeason(season));
        assertEquals(seasons.size(), seasonService.findAll().size());
    }

    @Test
    public void should_delete_season_by_id() {
        Season season = seasons.get(0);
        seasonService.addSeason(season);
        seasonService.deleteSeasonById(season.getId());
        assertNull(seasonService.findById(season.getId()));
    }

    @Test
    public void should_delete_season() {
        Season season = seasons.get(0);
        seasonService.addSeason(season);
        seasonService.deleteSeason(season);
        assertNull(seasonService.findById(season.getId()));
    }

    @Test
    public void should_delete_season_with_episodes() {
        Season season = seasonWithEpisodes();
        season.getEpisodes().forEach(episode -> episodeService.addEpisode(episode));
        seasonService.addSeason(season);
        seasonService.deleteSeason(season);
        episodeService.findAll().forEach(Assert::assertNull);
        assertNull(seasonService.findById(season.getId()));
    }

    @Test
    public void should_be_only_one() {
        seasons.add(new Season("World is our"));
        seasons.add(new Season("World is our"));

        seasons.forEach(season -> seasonService.addSeason(season));
        List<Season> seasonList = seasonService.findAll();
        for (Season s1 : seasonList) {
            int sameSeasons = 0;
            for (Season s2 : seasonList) {
                if (s1.getName().equals(s2.getName())) sameSeasons++;
                if (sameSeasons > 1) fail("Season with already existing name cannot be saved");
            }
        }
    }

    @After
    public void cleanUp() {
        seasonRepository.deleteAll();
        episodeRepository.deleteAll();
    }

    private ArrayList<Season> seasonsList() {
        ArrayList<Season> seasons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            seasons.add(new Season("Season" + i));
        }

        return seasons;
    }

    private Season seasonWithEpisodes() {
        Season season = new Season("Season");
        season.getEpisodes().add(new Episode("Episode", ""));

        return season;
    }
}