package it.maconsulting.teams.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.*;

/**
 * A Team that aggregates a certain number of workers. A {@link Team} object only
 * contains can only contain up to 10 members.
 * Members cannot have the same email.
 *
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@AggregateRoot
public class Team {
    /**
     * The unique ID of the team.
     */
    private final TeamId id;

    /**
     * The team's name.
     */
    private final String name;

    /**
     * The list of the team members.
     */
    private final Set<TeamMember> teamMembers;

    /**
     * Creates a {@link Team} entity without an ID. Use to create a new entity that is not yet
     * persisted.
     */
    public static Team withoutId(
            String name,
            Set<TeamMember> teamMembers) {
        return new Team(null, name, teamMembers);
    }

    /**
     * Creates a {@link Team} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static Team withId(
            TeamId teamId,
            String name,
            Set<TeamMember> teamMembers) {
        return new Team(teamId, name, teamMembers);
    }

    private Team(TeamId id, String name, Set<TeamMember> teamMembers) {
        this.id = id;
        this.name = name;
        this.teamMembers = teamMembers != null ? teamMembers : new HashSet<>();
    }

    Optional<TeamId> getId() {
        return Optional.ofNullable(id);
    }

    void addTeamMember(TeamMember teamMember) {
        if(teamMembers.size()==10) {
            throw new RuntimeException("Teams can have maximum 10 members");
        }
        teamMembers.add(teamMember);
    }

    @Value
    @ValueObject
    public static class TeamId {
        UUID value;
    }

    @Value
    @ValueObject
    static class TeamMember {
        UUID teamMemberId;
        Member.MemberId memberId;
        String email;
    }


}
