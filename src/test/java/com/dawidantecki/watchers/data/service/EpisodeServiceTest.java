package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest;
import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.repository.EpisodeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EpisodeServiceTest extends DatabaseConnectionTest {

    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private EpisodeRepository episodeRepository;

    private ArrayList<Episode> episodes;

    @Before
    public void setUp() {
        episodes = episodesList();
    }

    @Test
    public void should_save_episode() {
        Episode episode = episodes.get(0);
        episodeService.addEpisode(episode);
        assertEquals(episode, episodeService.findById(episode.getId()));
    }

    @Test
    public void should_find_episode_by_title() {
        Episode episode = episodes.get(0);
        episodeService.addEpisode(episode);
        assertEquals(episode, episodeService.findByTitle(episode.getTitle()));
    }

    @Test
    public void should_find_collection_of_episodes() {
        episodes.forEach(episode -> episodeService.addEpisode(episode));
        assertEquals(episodes.size(), episodeService.findAll().size());
    }

    @Test
    public void should_delete_episode() {
        Episode episode = episodes.get(0);
        episodeService.addEpisode(episode);
        episodeService.deleteEpisodeById(episode.getId());
        assertNull(episodeService.findById(episode.getId()));
    }

    @Test
    public void should_be_only_one() {
        episodes.add(new Episode("Hello World", ""));
        episodes.add(new Episode("Hello World", ""));

        episodes.forEach(episode -> episodeService.addEpisode(episode));

        List<Episode> episodes = episodeService.findAll();
        for (Episode ep1 : episodes) {
            int sameEpisodes = 0;
            for (Episode ep2 : episodes) {
                if (ep1.getTitle().equals(ep2.getTitle())) sameEpisodes++;
                if (sameEpisodes > 1) fail("Episode with already existing title cannot be saved");
            }
        }
    }

    @After
    public void cleanUp() {
        episodeRepository.deleteAll();
    }

    private ArrayList<Episode> episodesList() {
        ArrayList<Episode> episodes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            episodes.add(new Episode("Episode" + i, ""));
        }

        return episodes;
    }
}