package it.maconsulting.teams.domain.model.project;

import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.employee.EmployeeProjectRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.ValueObject;

import java.lang.reflect.Member;
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

    private final Set<Member> members;

    public Optional<ProjectId> getId() {
        return Optional.ofNullable(id);
    }

    public static Project withId(ProjectId id,
                                 String name,
                                 Set<Member> members) {
        return new Project(id, name, members);
    }

    @Builder
    Project(ProjectId id, String name, Set<Member> members) {
        this.id = id;
        this.name = name;
        this.members = members != null ? members : new HashSet<>();
    }

    public void addMember(Employee employee, EmployeeProjectRoleEnum role) {
        if (role.equals(EmployeeProjectRoleEnum.PROJECT_MANAGER)) {
            verifyOnlyOneProjectManager();
        }
        this.members.add(new Member(employee.getId().get(), role));

    }

    @Value
    @ValueObject
    public static class ProjectId {
        UUID value;
    }

    @Value
    public static class Member {
        Employee.EmployeeId employeeId;
        EmployeeProjectRoleEnum projectRole;
    }

    private void verifyOnlyOneProjectManager() {
        if(this.members.stream().anyMatch(it -> EmployeeProjectRoleEnum.PROJECT_MANAGER.equals(it.projectRole))) {
            throw new RuntimeException("This project already has a Project Manager");
        }
    }
}
