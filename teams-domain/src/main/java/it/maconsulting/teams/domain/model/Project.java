package it.maconsulting.teams.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@AggregateRoot
public class Project {

    private final ProjectId id;
    private final String name;

    private final Set<ProjectMember> members;

    public Optional<ProjectId> getId() {
        return Optional.ofNullable(id);
    }

    public static Project withId(ProjectId id,
                                 String name,
                                 Set<ProjectMember> members) {
        return new Project(id, name, members);
    }

    @Builder
    Project(ProjectId id, String name, Set<ProjectMember> members) {
        this.id = id;
        this.name = name;
        this.members = members != null ? members : new HashSet<>();
    }

    public void addMember(Member member, String role) {
        this.members.add(new ProjectMember(member.getId().get(), role));
    }

    @Value
    @ValueObject
    public static class ProjectId {
        UUID value;
    }

    @Value
    public static class ProjectMember {
        Member.MemberId memberId;
        String projectRole;
    }
}
