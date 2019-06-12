package com.dawidantecki.watchers.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "episodes_no")
    private Integer episodesNo;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;
    @OneToMany(mappedBy = "season", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude
    private List<Episode> episodes;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @CollectionTable(name = "series_id")
    private Series series;

    public Season() {

    }

    public Season(String name, Integer episodesNo) {
        this.name = name;
        this.episodesNo = episodesNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", episodesNo=" + episodesNo +
                ", release_date=" + release_date +
                '}';
    }
}
