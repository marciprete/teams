package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
@Entity
@Table(name = "team")
public class TeamEntity {

    @Id
    private UUID id;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "team", cascade = {CascadeType.ALL})
    Set<TeamMemberEntity> members;

    public void addMember(EmployeeEntity member, String email) {
        TeamMemberEntity teamMemberEntity = new TeamMemberEntity(this, member, email);
        members.add(teamMemberEntity);
        member.getTeams().add(teamMemberEntity);
    }
}
