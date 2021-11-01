package com.dawidantecki.watchers.data.entity;

import com.dawidantecki.watchers.data.entity.enums.RoleName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "name")
    private RoleName roleName;

    public Role() {
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
}
