package com.dawidantecki.watchers.data.entity;

import com.dawidantecki.watchers.data.entity.enums.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
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
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "country")
    private String country;
    @Column(name = "director")
    private String director;
    @Column(name = "release_date")
    private String release_date;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "series_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Season> seasons = new LinkedList<>();

    public Series() {

    }

    public Series(String title, String description, Category category, String country, String director) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.country = country;
        this.director = director;
    }
}

