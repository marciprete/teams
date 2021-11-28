package it.maconsulting.teams.application.service.project;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.microkernel.exceptions.EntityNotFoundException;
import it.maconsulting.teams.application.member.port.out.ReadMemberPort;
import it.maconsulting.teams.application.project.port.in.AddProjectMemberUseCase;
import it.maconsulting.teams.application.project.port.in.request.AddProjectMemberCommand;
import it.maconsulting.teams.application.project.port.out.ModifyProjectPort;
import it.maconsulting.teams.application.project.port.out.ReadProjectPort;
import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.domain.model.Project;
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

    private final ReadMemberPort readMemberPort;
    private final ReadProjectPort readProjectPort;
    private final ModifyProjectPort modifyProjectPort;

    @Override
    public void addProjectMember(AddProjectMemberCommand command) {
        Member member = readMemberPort.findById(command.getMemberId()).orElseThrow(
                ()-> new EntityNotFoundException("Member", command.getMemberId().toString()));
        Project project = readProjectPort.fetchProjectWithMembersById(command.getProjectId()).orElseThrow(
                ()-> new EntityNotFoundException("Project", command.getProjectId().toString()));

        project.addMember(member, command.getRole());

        modifyProjectPort.update(project);
    }
}
