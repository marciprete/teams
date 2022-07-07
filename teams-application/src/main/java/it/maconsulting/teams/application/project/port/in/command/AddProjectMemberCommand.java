package it.maconsulting.teams.application.project.port.in.command;

import it.maconsulting.microkernel.validation.SelfValidating;
import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.employee.EmployeeProjectRoleEnum;
import it.maconsulting.teams.domain.model.project.Project;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Value
@EqualsAndHashCode(callSuper = false)
public class AddProjectMemberCommand extends SelfValidating<AddProjectMemberCommand> {
    @NotNull Project.ProjectId projectId;
    @NotNull Employee.EmployeeId employeeId;
    @NotNull EmployeeProjectRoleEnum role;

    public AddProjectMemberCommand(Project.ProjectId projectId, Employee.EmployeeId employeeId, EmployeeProjectRoleEnum role) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.role = role;
        this.validateSelf();
    }
}
