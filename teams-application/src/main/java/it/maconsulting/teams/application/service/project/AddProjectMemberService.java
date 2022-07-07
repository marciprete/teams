package it.maconsulting.teams.application.service.project;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.microkernel.exceptions.EntityNotFoundException;
import it.maconsulting.teams.application.employee.port.out.ReadEmployeePort;
import it.maconsulting.teams.application.project.port.in.AddProjectMemberUseCase;
import it.maconsulting.teams.application.project.port.in.command.AddProjectMemberCommand;
import it.maconsulting.teams.application.project.port.out.ModifyProjectPort;
import it.maconsulting.teams.application.project.port.out.ReadProjectPort;
import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.project.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class AddProjectMemberService implements AddProjectMemberUseCase {

    private final ReadEmployeePort readEmployeePort;
    private final ReadProjectPort readProjectPort;
    private final ModifyProjectPort modifyProjectPort;

    @Override
    public void addProjectMember(AddProjectMemberCommand command) {
        Employee employee = readEmployeePort.findById(command.getEmployeeId()).orElseThrow(
                ()-> new EntityNotFoundException("Employee", command.getEmployeeId().toString()));
        Project project = readProjectPort.fetchProjectWithMembersById(command.getProjectId()).orElseThrow(
                ()-> new EntityNotFoundException("Project", command.getProjectId().toString()));

        project.addMember(employee, command.getRole());

        modifyProjectPort.update(project);
    }
}
