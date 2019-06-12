package com.dawidantecki.watchers.data.entity;

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
    @Column(name = "name")
    private String name;
    @Column(name = "episodes_no")
    private Integer episodesNo;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date release_date;
    @ToString.Exclude
    @OneToMany(mappedBy = "season", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude
    private List<Episode> episodes;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @CollectionTable(name = "series_id")
    private Series series;

    public Season() {

    }

    public Season(String name, Integer episodesNo) {
        this.name = name;
        this.episodesNo = episodesNo;
    }

}
