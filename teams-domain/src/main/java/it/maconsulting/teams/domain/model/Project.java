package it.maconsulting.teams.domain.model;

import lombok.*;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@AggregateRoot
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public void addMember(Member member) {

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
