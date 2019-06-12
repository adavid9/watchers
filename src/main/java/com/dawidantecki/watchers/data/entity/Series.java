package com.dawidantecki.watchers.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@ToString
@Entity
@EqualsAndHashCode
@Table(name = "series")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "country")
    private String country;
    @Column(name = "director")
    private String director;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "series_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Season> seasons = new LinkedList<>();

    public Series() {

    }

    public Series(String title, String description, String country, String director) {
        this.title = title;
        this.description = description;
        this.country = country;
        this.director = director;
    }
}

