package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
@Entity
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
    private MemberEntity member;

}
