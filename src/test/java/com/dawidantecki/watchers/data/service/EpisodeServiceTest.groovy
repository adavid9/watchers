package com.dawidantecki.watchers.data.service

import com.dawidantecki.watchers.data.entity.Episode
import com.dawidantecki.watchers.data.repository.EpisodeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class EpisodeServiceTest extends Specification {

    @Autowired
    private EpisodeService episodeService
    @Autowired
    private EpisodeRepository episodeRepository

    def cleanup() {
        episodeRepository.deleteAll()
    }

    def "should add episode to the database"() {
        when:
        Episode episode = new Episode("title", "description")
        and:
        episodeService.addEpisode(episode)
        then:
        episode == episodeService.findById(episode.id)
    }

    def "should delete episode if previously added to the database"() {
        when:
        Episode episode = new Episode("title", "description")
        and:
        episodeService.addEpisode(episode)
        then:
        episodeService.findById(episode.id)
        when:
        episodeService.deleteEpisodeById(episode.id)
        then:
        !episodeService.findById(episode.id)
    }

    def "should return valid size of episodes added to the database"() {
        given:
        List<Episode> episodes = []
        when:
        episodes.add(new Episode("title1", "description1"))
        episodes.add(new Episode("title2", "description2"))
        and:
        episodeService.addEpisodes(episodes)
        then:
        episodeService.findAll().size() == episodes.size()
    }
}
