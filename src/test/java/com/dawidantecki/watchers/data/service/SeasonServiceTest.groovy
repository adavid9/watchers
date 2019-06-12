package com.dawidantecki.watchers.data.service

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest
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

    def cleanup() {
        seasonRepository.deleteAll()
    }

    def "should add season to the database"() {
        when:
        Season season = new Season("Season 1", 10)
        and:
        seasonService.addSeason(season)
        then:
        season == seasonService.findById(season.id)
    }

    def "should remove season from the database when added previously to the database"() {
        when:
        Season season = new Season("Season 1", 10)
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
        seasons.add(new Season("Season 1", 10))
        seasons.add(new Season("Season 2", 8))
        and:
        seasonService.addSeasons(seasons)
        then:
        seasonService.findAll().size() == seasons.size()
    }

    def "should find season by name"() {
        when:
        Season season = new Season("Season1", 10)
        and:
        seasonService.addSeason(season)
        then:
        seasonService.findByName(season.name)
    }

    def "should delete a collection of seasons"() {
        given:
        List<Season> seasons = Arrays.asList(new Season("season1", 5), new Season("season2", 10))
        when:
        seasonService.addSeasons(seasons)
        then:
        seasonService.findAll().size() == seasons.size()
        when:
        seasonService.deleteSeasons(seasons)
        then:
        seasonService.findAll().size() == 0
    }
}
