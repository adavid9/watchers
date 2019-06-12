package com.dawidantecki.watchers.data.service

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest
import com.dawidantecki.watchers.data.entity.Series
import com.dawidantecki.watchers.data.repository.SeriesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SeriesServiceTest extends DatabaseConnectionTest {

    @Autowired
    private SeriesService seriesService
    @Autowired
    private SeriesRepository seriesRepository

    def cleanup() {
        seriesRepository.deleteAll()
    }

    def "should add series to the database"() {
        when:
        Series series = new Series("Series title", "Series description", "USA", "director")
        and:
        seriesService.addSeries(series)
        then:
        series == seriesService.findById(series.id)
    }

    def "should delete series from the database if previously added"() {
        when:
        Series series = new Series("Series title", "Series description", "USA", "director")
        and:
        seriesService.addSeries(series)
        then:
        seriesService.findById(series.id)
        when:
        seriesService.deleteSeries(series.id)
        then:
        !seriesService.findById(series.id)
    }

    def "should add collection of series to the database"() {
        given:
        List<Series> series = []
        when:
        series.add(new Series("Series title", "Series description", "USA", "director"))
        series.add(new Series("Series title2", "Series description2", "USA", "director"))
        and:
        seriesService.addSeries(series)
        then:
        seriesService.findAll().size() == series.size()
    }

    def "should find series by title"() {
        when:
        Series series = new Series("series1", "xxx", "xxx", "xxx")
        and:
        seriesService.addSeries(series)
        then:
        seriesService.findByTitle(series.title)
    }

    def "should delete series by title"() {
        given:
        Series series = new Series("series1", "xxx", "xxx", "xxx")
        when:
        seriesService.addSeries(series)
        and:
        seriesService.deleteSeries(series.title)
        then:
        !seriesService.findByTitle(series.title)
    }
}
