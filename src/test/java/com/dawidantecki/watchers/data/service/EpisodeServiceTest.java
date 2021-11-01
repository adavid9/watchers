package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.repository.EpisodeRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EpisodeServiceTest {

    @Mock
    private EpisodeRepository episodeRepository;

    @InjectMocks
    private EpisodeService episodeService;

    @Test
    public void when_find_by_id_then_episode_should_be_returned() {
        final Optional<Episode> expected = Optional.of(Episode.builder()
                .title("Episode")
                .build()
        );
        when(episodeRepository.findById(anyLong())).thenReturn(expected);

        final Episode actual = episodeService.findById(1L);

        assertEquals(expected.get(), actual);
    }

    @Test
    public void when_find_by_title_then_episode_should_be_returned() {
        final Episode expected = Episode.builder()
                .title("Episode")
                .build();
        when(episodeRepository.findByTitle(anyString())).thenReturn(expected);

        final Episode actual = episodeService.findByTitle("Episode");

        assertEquals(expected, actual);
    }

    @Test
    public void when_find_all_then_episodes_should_be_returned() {
        final List<Episode> expected = Lists.newArrayList(
                Episode.builder()
                        .title("Episode_1")
                        .build(),
                Episode.builder()
                        .title("Episode_2")
                        .build()
        );
        when(episodeRepository.findAll()).thenReturn(expected);

        final List<Episode> actual = episodeService.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void when_add_episode_then_episode_should_be_saved() {
        final Episode episode = Episode.builder()
                .title("Episode")
                .build();

        episodeService.addEpisode(episode);

        verify(episodeRepository).save(eq(episode));
    }

    @Test
    public void when_delete_episode_by_id_then_episode_should_be_deleted() {
        final Episode episode = Episode.builder()
                .title("Episode")
                .build();
        when(episodeRepository.findById(anyLong())).thenReturn(Optional.of(episode));

        episodeService.deleteEpisodeById(1L);

        verify(episodeRepository).delete(eq(episode));
    }
}