package com.dawidantecki.watchers.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
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
