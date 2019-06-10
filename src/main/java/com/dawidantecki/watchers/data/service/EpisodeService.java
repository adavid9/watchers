package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Episode;
import com.dawidantecki.watchers.data.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
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

    public void addEpisode(Episode episode) {
        if (episode != null)
            episodeRepository.save(episode);
    }

    public void addEpisodes(Collection<Episode> episodes) {
        if (episodes.size() > 0)
            episodes.forEach(x -> {
                if (x != null)
                    addEpisode(x);
            });
    }

    public void deleteEpisodeById(long id) {
        Episode episode = findById(id);
        if (episode != null)
            episodeRepository.delete(episode);
    }
}
