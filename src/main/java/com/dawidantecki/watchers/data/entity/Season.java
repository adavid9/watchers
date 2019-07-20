package com.dawidantecki.watchers.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "release_date")
    private String release_date;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "season_id")
    private List<Episode> episodes = new ArrayList<>();
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @CollectionTable(name = "series_id")
    private Series series;

    public Season() {

    }

    public Season(String name) {
        this.name = name;
    }

}
