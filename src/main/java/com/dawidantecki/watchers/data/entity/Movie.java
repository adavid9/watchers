package com.dawidantecki.watchers.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;
    @Column(name = "country")
    private String country;
    @Column(name = "category")
    private String category;

    public Movie() {

    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, String description, Date release_date, String country, String category) {
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.country = country;
        this.category = category;
    }
}
