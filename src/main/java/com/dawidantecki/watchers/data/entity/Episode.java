package com.dawidantecki.watchers.data.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date release_date;
    @ManyToOne
    @ToString.Exclude
    private Season season;

    public Episode() {

    }

    public Episode(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
