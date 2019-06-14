package com.dawidantecki.watchers.data.service

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest
import com.dawidantecki.watchers.data.entity.Episode
import com.dawidantecki.watchers.data.entity.Season
import com.dawidantecki.watchers.data.repository.SeasonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SeasonServiceTest extends DatabaseConnectionTest {

    @Autowired
    private SeasonService seasonService
    @Autowired
    private SeasonRepository seasonRepository
    @Autowired
    private EpisodeService episodeService

    def cleanup() {
        seasonRepository.deleteAll()
    }

    def "should add season to the database"() {
        when:
        Season season = new Season("Season 1")
        and:
        seasonService.addSeason(season)
        then:
        season == seasonService.findById(season.id)
    }

    def "should remove season from the database when added previously to the database"() {
        when:
        Season season = new Season("Season 1")
        and:
        seasonService.addSeason(season)
        then:
        seasonService.findById(season.id)
        when:
        seasonService.deleteSeasonById(season.id)
        then:
        !seasonService.findById(season.id)
    }

    def "should add collection of season to the database"() {
        given:
        List<Season> seasons = []
        when:
        seasons.add(new Season("Season 1"))
        seasons.add(new Season("Season 2"))
        and:
        seasonService.addSeasons(seasons)
        then:
        seasonService.findAll().size() == seasons.size()
    }

    def "should find season by name"() {
        when:
        Season season = new Season("Season1")
        and:
        seasonService.addSeason(season)
        then:
        seasonService.findByName(season.name)
    }

    def "should delete a collection of seasons"() {
        given:
        List<Season> seasons = Arrays.asList(new Season("season1"), new Season("season2"))
        when:
        seasonService.addSeasons(seasons)
        then:
        seasonService.findAll().size() == seasons.size()
        when:
        seasonService.deleteSeasons(seasons)
        then:
        seasonService.findAll().size() == 0
    }

    def "should delete episodes if a season is deleted"() {
        given:
        List<Episode> episodes = []
        Season season = new Season("season")
        when:
        episodes.add(new Episode("title", ""))
        episodes.add(new Episode("title2", ""))
        seasonService.addSeason(season)
        and:
        episodes.forEach({ x -> x.setSeason(season) })
        episodeService.addEpisodes(episodes)
        then:
        episodeService.findAll().size() == episodes.size()
        seasonService.findByName(season.name)
        when:
        seasonService.deleteSeasonByName(season.name)
        then:
        episodeService.findAll().size() == 0
        !seasonService.findByName(season.name)
    }
}
