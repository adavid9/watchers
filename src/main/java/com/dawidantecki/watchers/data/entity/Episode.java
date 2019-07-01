package com.dawidantecki.watchers.data.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "release_date")
    private String release_date;
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Season season;

    public Episode() {

    }

    public Episode(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Episode(String title, String description, String release_date, Season season) {
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.season = season;
    }
}
