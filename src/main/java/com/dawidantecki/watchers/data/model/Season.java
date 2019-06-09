package com.dawidantecki.watchers.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "episodes_no")
    private Integer episodesNo;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;
    @OneToMany(mappedBy = "season", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude
    private List<Episode> episodes;
    @ManyToOne
    private Series series;

    public Season() {

    }

    public Season(Integer episodesNo) {
        this.episodesNo = episodesNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEpisodesNo() {
        return episodesNo;
    }

    public void setEpisodesNo(Integer episodesNo) {
        this.episodesNo = episodesNo;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
