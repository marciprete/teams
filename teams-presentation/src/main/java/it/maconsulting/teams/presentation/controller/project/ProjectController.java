package it.maconsulting.teams.presentation.controller.project;

import it.maconsulting.teams.application.project.port.in.AddProjectMemberUseCase;
import it.maconsulting.teams.application.project.port.in.ChangeProjectNameUseCase;
import it.maconsulting.teams.application.project.port.in.request.AddProjectMemberCommand;
import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.domain.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("projects")
@RequiredArgsConstructor
public class ProjectController {
    private final AddProjectMemberUseCase addProjectMemberService;
    private final ChangeProjectNameUseCase changeProjectNameService;

    @PutMapping(path = "/project/{projectId}/add/{memberId}/role/{role}")
    public ResponseEntity<Void> addProjectMember(
            @PathVariable UUID projectId,
            @PathVariable UUID memberId,
            @PathVariable String role) {

        addProjectMemberService.addProjectMember(
                new AddProjectMemberCommand(
                        new Project.ProjectId(projectId),
                        new Member.MemberId(memberId),
                        role
                ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
