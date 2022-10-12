package it.maconsulting.teams.domain.model.project;

import it.maconsulting.microkernel.exceptions.DomainException;
import it.maconsulting.teams.domain.model.employee.Employee;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
        verifyDuplicateMember(employee);
        this.members.add(new Member(employee.getId().get(), "", role));
    }

    public void changeRole(Member member) {
        if (member.getProjectRole().equals(EmployeeProjectRoleEnum.PROJECT_MANAGER)) {
            verifyOnlyOneProjectManager();
        }
        Member existing = members.stream().filter(it -> member.getEmployeeId().equals(it.employeeId)).findFirst()
                .orElseThrow(() -> new DomainException("Failed getting project member"));
        members.remove(existing);
        members.add(member);
    }

    @Value
    @ValueObject
    public static class ProjectId {
        UUID value;
    }

    @Value
    public static class Member {
        Employee.EmployeeId employeeId;
        String fullName;
        @EqualsAndHashCode.Exclude
        EmployeeProjectRoleEnum projectRole;
    }

    private void verifyOnlyOneProjectManager() {
        if(this.members.stream().anyMatch(it -> EmployeeProjectRoleEnum.PROJECT_MANAGER.equals(it.projectRole))) {
            throw new DomainException("This project already has a Project Manager");
        }
    }

    private void verifyDuplicateMember(Employee newMember) {
        if(this.members.stream().anyMatch(member -> newMember.getId().get().equals(member.getEmployeeId()))) {
            throw new DomainException("The user is already present in the project");
        }
    }
}
