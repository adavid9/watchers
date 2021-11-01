package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.repository.SeasonRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SeasonServiceTest {

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private EpisodeService episodeService;

    @InjectMocks
    private SeasonService seasonService;

    @Test
    public void when_find_by_id_then_season_should_be_returned() {
        final Optional<Season> expected = Optional.of(Season.builder()
                .name("Season")
                .build()
        );
        when(seasonRepository.findById(anyLong())).thenReturn(expected);

        final Season actual = seasonService.findById(1L);

        assertEquals(expected.get(), actual);
    }

    @Test
    public void when_find_by_name_then_season_should_be_returned() {
        final Season expected = Season.builder()
                .name("Season")
                .build();
        when(seasonRepository.findByName(anyString())).thenReturn(expected);

        final Season actual = seasonService.findByName("Season");

        assertEquals(expected, actual);
    }

    @Test
    public void when_find_all_then_seasons_should_be_returned() {
        final List<Season> expected = Lists.newArrayList(
                Season.builder()
                        .name("Season_1")
                        .build(),
                Season.builder()
                        .name("Season_2")
                        .build()
        );
        when(seasonRepository.findAll()).thenReturn(expected);

        final List<Season> actual = seasonService.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void when_add_season_then_season_should_be_saved() {
        final Season season = Season.builder()
                .name("Season")
                .build();

        seasonService.addSeason(season);

        verify(seasonRepository).save(eq(season));
    }

    @Test
    public void when_delete_season_by_id_then_season_with_all_episodes_should_be_deleted() {
        final Season season = Season.builder()
                .name("Season")
                .build();
        final Episode episode = mock(Episode.class);
        season.setEpisodes(Collections.singletonList(episode));
        when(episode.getId()).thenReturn(1L);
        when(seasonRepository.findById(anyLong())).thenReturn(Optional.of(season));

        seasonService.deleteSeasonById(1L);

        verify(episodeService).deleteEpisodeById(eq(1L));
        verify(seasonRepository).delete(eq(season));
    }
}