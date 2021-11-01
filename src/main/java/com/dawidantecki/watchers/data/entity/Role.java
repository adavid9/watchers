package com.dawidantecki.watchers.data.entity;

import com.dawidantecki.watchers.data.entity.enums.RoleName;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
