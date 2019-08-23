package com.dawidantecki.watchers.data.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "question")
	private String question;
    @Column(name = "answer")
	private String answer;
    @Column(name = "password")
    private String password;
    @Transient
    @EqualsAndHashCode.Exclude
    private String confirmPassword;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Movie> user_movies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JoinTable(name = "user_series", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "series_id"))
    private List<Series> userSeries = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JoinTable(name = "user_seasons", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "season_id"))
	private List<Season> userSeasons = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JoinTable(name = "user_episodes", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "episode_id"))
	private List<Episode> userEpisodes = new ArrayList<>();

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String email, String question, String answer,
			String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.question = question;
        this.answer = answer;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
