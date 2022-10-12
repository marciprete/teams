package it.maconsulting.teams.application.service.project;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.microkernel.exceptions.EntityNotFoundException;
import it.maconsulting.teams.application.project.port.in.ChangeProjectRoleUseCase;
import it.maconsulting.teams.application.project.port.in.command.ProjectMemberCommand;
import it.maconsulting.teams.application.project.port.out.ModifyProjectPort;
import it.maconsulting.teams.application.project.port.out.ReadProjectPort;
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
public class ChangeProjectRoleService implements ChangeProjectRoleUseCase {

    private final ReadProjectPort readProjectPort;
    private final ModifyProjectPort modifyProjectPort;
    @Override
    public void changeProjectRole(ProjectMemberCommand command) {
        Project project = readProjectPort.fetchProjectWithMembersById(command.getProjectId()).orElseThrow(
                ()-> new EntityNotFoundException("Project", command.getProjectId().toString()));
        Project.Member projectMember = project.getMembers().stream().filter(member -> member.getEmployeeId().equals(command.getEmployeeId())).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Project", command.getProjectId().toString()));

        if(!projectMember.getProjectRole().equals(command.getRole())) {
            project.changeRole(new Project.Member(projectMember.getEmployeeId(),
                    projectMember.getFullName(),
                    command.getRole()));
        }
        modifyProjectPort.update(project);
    }
}
