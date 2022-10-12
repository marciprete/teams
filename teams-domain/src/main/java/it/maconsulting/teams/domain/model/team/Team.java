package it.maconsulting.teams.domain.model.team;

import it.maconsulting.teams.domain.model.employee.Employee;
import lombok.Getter;
import lombok.Value;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.*;

/**
 * A Team that aggregates a certain number of workers.
 * A {@link Team} object can only contain up to 10 members.
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
     * Creates a {@link Team} entity without an ID. Used to create a new entity that is not yet
     * persisted.
     */
    public static Team withoutId(
            String name,
            Set<TeamMember> teamMembers) {
        return new Team(new TeamId(UUID.randomUUID()), name, teamMembers);
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

    public Optional<TeamId> getId() {
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
    public static class TeamMember {
//        UUID teamMemberId;
        Employee.EmployeeId employeeId;
        String email;
    }


}
