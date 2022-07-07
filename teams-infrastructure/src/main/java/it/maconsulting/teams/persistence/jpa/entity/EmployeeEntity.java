package it.maconsulting.teams.persistence.jpa.entity;

import lombok.*;

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
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    private UUID id;

    private String name;

    private String surname;

    private String email;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "member")
    private Set<ProjectMemberEntity> projects;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "member")
    Set<TeamMemberEntity> teams;

}
