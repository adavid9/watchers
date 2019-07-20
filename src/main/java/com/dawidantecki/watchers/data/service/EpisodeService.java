package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EpisodeService {

    private EpisodeRepository episodeRepository;

    @Autowired
    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public Episode findById(long id) {
        return episodeRepository.findById(id).orElse(null);
    }

    public List<Episode> findAll() {
        return episodeRepository.findAll();
    }

    public Episode findByTitle(String title) {
        return episodeRepository.findByTitle(title);
    }

    public void addEpisode(Episode episode) {
        if (episode == null)
            return;
        Episode ep = findByTitle(episode.getTitle());
        if (ep != null) {
            ep.setTitle(episode.getTitle());
            ep.setDescription(episode.getDescription());
            ep.setRelease_date(episode.getRelease_date());
            ep.setSeason(episode.getSeason());
            episodeRepository.save(ep);
        } else {
            episodeRepository.save(episode);
        }
    }

    public void deleteEpisodeById(long id) {
        Episode episode = findById(id);
        if (episode != null)
            episodeRepository.delete(episode);
    }
}
