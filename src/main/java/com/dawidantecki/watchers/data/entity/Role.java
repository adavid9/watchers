package com.dawidantecki.watchers.data.entity;

import com.dawidantecki.watchers.data.entity.enums.RoleName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
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

    private Role() {
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
}
