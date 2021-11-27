package it.maconsulting.teams.application.project.service;

import it.kiranet.fidcare.microkernel.exceptions.EntityNotFoundException;
import it.maconsulting.teams.application.member.port.in.ReadMemberPort;
import it.maconsulting.teams.application.project.port.in.AddProjectMemberUseCase;
import it.maconsulting.teams.application.project.port.in.request.AddProjectMemberCommand;
import it.maconsulting.teams.application.project.port.out.ModifyProjectPort;
import it.maconsulting.teams.application.project.port.out.ReadProjectPort;
import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.domain.model.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
//@PersistenceAdapter
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

        project.addMember(member);

        modifyProjectPort.save(project);
    }
}
