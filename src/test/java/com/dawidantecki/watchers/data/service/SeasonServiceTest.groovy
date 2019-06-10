package com.dawidantecki.watchers.data.service

import com.dawidantecki.watchers.data.entity.Season
import com.dawidantecki.watchers.data.repository.SeasonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SeasonServiceTest extends Specification {

    @Autowired
    SeasonService seasonService
    @Autowired
    SeasonRepository seasonRepository

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
}