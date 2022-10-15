package it.maconsulting.teams.presentation.controller.project;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import it.maconsulting.microkernel.annotations.WebAdapter;
import it.maconsulting.teams.application.project.port.in.AddProjectMemberUseCase;
import it.maconsulting.teams.application.project.port.in.ChangeProjectRoleUseCase;
import it.maconsulting.teams.application.project.port.in.CreateProjectUseCase;
import it.maconsulting.teams.application.project.port.in.ReadProjectUseCase;
import it.maconsulting.teams.application.project.port.in.command.ProjectMemberCommand;
import it.maconsulting.teams.domain.model.project.EmployeeProjectRoleEnum;
import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.project.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Max;
import java.security.Principal;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@WebAdapter
@RestController
@Api(tags = "Projects")
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {
    private final AddProjectMemberUseCase addProjectMemberUseCase;
    private final ChangeProjectRoleUseCase changeProjectRoleUseCase;
    private final ReadProjectUseCase readProjectUseCase;
    private final CreateProjectUseCase createProjectUseCase;

    private final ProjectDtoMapper projectDtoMapper = new ProjectDtoMapper();

    @PostMapping("/{name}")
    @ApiOperation(value = "Create a Project with a name. Project name is unique",
            tags = {"Projects"},
            authorizations = @Authorization(value = "create",
                    scopes = {@AuthorizationScope(description = "Create Project scope",
                            scope = "project:create")}))
    public ResponseEntity<ProjectDto> create(@PathVariable String name,
                                       @ApiIgnore Principal principal) {
        return new ResponseEntity<>(projectDtoMapper.toDto(createProjectUseCase.createProject(name)),
                HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "List all the existing Projects",
            tags = {"Projects"},
            authorizations = @Authorization(value = "create",
                    scopes = {@AuthorizationScope(description = "List Project scope",
                            scope = "project:list")}))
    public ResponseEntity<Page<ProjectDto>> listProjects(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") @Max(100) int size) {
        return ResponseEntity.ok(
                readProjectUseCase.list(PageRequest.of(page, size)).map(
                it -> new ProjectDto(it.getId().get().getValue(), it.getName()))
        );
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "Get a Project by its name and retrieve it with its details",
            tags = {"Projects"},
            authorizations = @Authorization(value = "getDetails",
                    scopes = {@AuthorizationScope(description = "Get Project details scope",
                            scope = "project:view-details")}))
    public ResponseEntity<ProjectDetailsDto> getDetails(@PathVariable String name) {
        return ResponseEntity.ok(readProjectUseCase.readProjectDetails(name)
                .map(projectDtoMapper::toProjectDetailsDto)
                .orElse(new ProjectDetailsDto()));
    }

    @PutMapping(path = "/project/{projectId}/add/{memberId}/role/{role}")
    @ApiOperation(value = "Add a Member to a Project with a role.",
            tags = {"Projects"},
            authorizations = @Authorization(value = "add-member",
                    scopes = {@AuthorizationScope(description = "Add Member to Project scope",
                            scope = "project:add-member")}))
    public ResponseEntity<Void> addProjectMember(
            @PathVariable UUID projectId,
            @PathVariable UUID memberId,
            @PathVariable EmployeeProjectRoleEnum role) {

        addProjectMemberUseCase.addProjectMember(
                new ProjectMemberCommand(
                        new Project.ProjectId(projectId),
                        new Employee.EmployeeId(memberId),
                        role
                ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/project/{projectId}/change/{memberId}/role/{role}")
    @ApiOperation(value = "Change te Member role in an existing Project.",
            tags = {"Projects"},
            authorizations = @Authorization(value = "change-role",
                    scopes = {@AuthorizationScope(description = "Change Project Role scope",
                            scope = "project:change-role")}))
    public ResponseEntity<Void> changeMemberRole(
            @PathVariable UUID projectId,
            @PathVariable UUID memberId,
            @PathVariable EmployeeProjectRoleEnum role) {

        changeProjectRoleUseCase.changeProjectRole(
                new ProjectMemberCommand(
                        new Project.ProjectId(projectId),
                        new Employee.EmployeeId(memberId),
                        role
                ));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
