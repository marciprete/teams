package it.maconsulting.teams.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity {

    @Id
    private UUID id;

    private String name;

    private String surname;

    private String email;

//    @OneToMany(
//            mappedBy = "member",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<ProjectMemberEntity> projects;
}
