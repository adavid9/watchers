package com.dawidantecki.watchers.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
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
