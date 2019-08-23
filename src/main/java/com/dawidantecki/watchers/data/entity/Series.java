package com.dawidantecki.watchers.data.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dawidantecki.watchers.data.entity.enums.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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

