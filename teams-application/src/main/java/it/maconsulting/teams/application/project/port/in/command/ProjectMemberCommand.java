package it.maconsulting.teams.application.project.port.in.command;

import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.project.EmployeeProjectRoleEnum;
import it.maconsulting.teams.domain.model.project.Project;
import lombok.Value;

import javax.validation.constraints.NotNull;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Value
public class ProjectMemberCommand {
    @NotNull Project.ProjectId projectId;
    @NotNull Employee.EmployeeId employeeId;
    @NotNull EmployeeProjectRoleEnum role;

    public ProjectMemberCommand(Project.ProjectId projectId, Employee.EmployeeId employeeId, EmployeeProjectRoleEnum role) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.role = role;
    }
}
