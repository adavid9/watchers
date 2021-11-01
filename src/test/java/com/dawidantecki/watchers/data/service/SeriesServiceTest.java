package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Season;
import com.dawidantecki.watchers.data.entity.Series;
import com.dawidantecki.watchers.data.repository.SeriesRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SeriesServiceTest {

    @Mock
    private SeriesRepository seriesRepository;

    @Mock
    private SeasonService seasonService;

    @InjectMocks
    private SeriesService seriesService;

    @Test
    public void when_find_by_id_then_series_should_be_returned() {
        final Optional<Series> expected = Optional.of(mock(Series.class));
        when(seriesRepository.findById(anyLong())).thenReturn(expected);

        final Series actual = seriesService.findById(1L);

        assertEquals(expected.get(), actual);
    }

    @Test
    public void when_find_by_title_then_series_should_be_returned() {
        final Series expected = mock(Series.class);
        when(seriesRepository.findByTitle(anyString())).thenReturn(expected);

        final Series actual = seriesService.findByTitle("Series");

        assertEquals(expected, actual);
    }

    @Test
    public void when_find_all_then_series_should_be_returned() {
        final List<Series> expected = Lists.newArrayList(
                mock(Series.class),
                mock(Series.class)
        );
        when(seriesRepository.findAll()).thenReturn(expected);

        final List<Series> actual = seriesService.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void when_add_series_then_series_should_be_saved() {
        final Series series = mock(Series.class);

        seriesService.addSeries(series);

        verify(seriesRepository).save(eq(series));
    }

    @Test
    public void when_delete_series_then_series_should_be_deleted() {
        final Series series = mock(Series.class);
        final Season season = mock(Season.class);
        when(series.getSeasons()).thenReturn(Collections.singletonList(season));

        seriesService.deleteSeries(series);

        verify(seasonService).deleteSeason(eq(season));
        verify(seriesRepository).delete(eq(series));
    }
}