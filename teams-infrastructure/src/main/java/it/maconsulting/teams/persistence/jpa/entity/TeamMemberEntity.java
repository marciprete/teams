package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "team_member")
public class TeamMemberEntity {

    @EmbeddedId
    private TeamMemberId id;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    private TeamEntity team;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    private EmployeeEntity member;

    public TeamMemberEntity(TeamEntity team, EmployeeEntity member, String email) {
        this.email = email;
        this.team = team;
        this.member = member;
        this.id = new TeamMemberId(team.getId(), member.getId());
    }
}
