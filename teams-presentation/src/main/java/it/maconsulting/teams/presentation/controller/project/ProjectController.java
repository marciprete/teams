package it.maconsulting.teams.presentation.controller.project;

import io.swagger.annotations.Api;
import it.maconsulting.microkernel.annotations.WebAdapter;
import it.maconsulting.teams.application.project.port.in.AddProjectMemberUseCase;
import it.maconsulting.teams.application.project.port.in.ChangeProjectNameUseCase;
import it.maconsulting.teams.application.project.port.in.CreateProjectUseCase;
import it.maconsulting.teams.application.project.port.in.ReadProjectUseCase;
import it.maconsulting.teams.application.project.port.in.request.AddProjectMemberCommand;
import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.domain.model.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@WebAdapter
@RestController
@Api(tags = "Project")
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {
    private final AddProjectMemberUseCase addProjectMemberService;
    private final ChangeProjectNameUseCase changeProjectNameService;
    private final ReadProjectUseCase readProjectUseCase;
    private final CreateProjectUseCase createProjectUseCase;

    @GetMapping
    public ResponseEntity<Page<ProjectDto>> listProjects(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") @Max(100) int size) {
        return ResponseEntity.ok(
                readProjectUseCase.list(PageRequest.of(page, size)).map(
                it -> new ProjectDto(it.getId().get().getValue(), it.getName()))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getDetails(@PathVariable UUID id) {
        readProjectUseCase.readProjectDetails(new Project.ProjectId(id));
        return null;
    }

    @PostMapping("/{name}")
    public ResponseEntity<Void> create(@PathVariable String name) {
        createProjectUseCase.createProject(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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
