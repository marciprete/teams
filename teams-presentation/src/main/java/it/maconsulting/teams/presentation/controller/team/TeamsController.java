package it.maconsulting.teams.presentation.controller.team;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import it.maconsulting.microkernel.annotations.WebAdapter;
import it.maconsulting.teams.application.team.port.in.AddTeamMemberUseCase;
import it.maconsulting.teams.application.team.port.in.CreateTeamUseCase;
import it.maconsulting.teams.application.team.port.in.ReadTeamsUseCase;
import it.maconsulting.teams.application.team.port.in.command.TeamCommand;
import it.maconsulting.teams.application.team.port.in.command.TeamMemberCommand;
import it.maconsulting.teams.presentation.controller.project.ProjectDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.security.Principal;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@WebAdapter
@RestController
@Api(tags = {"Teams"})
@RequiredArgsConstructor
@RequestMapping("teams")
public class TeamsController {

    private final TeamDtoMapper teamDtoMapper = new TeamDtoMapper();
    private final ReadTeamsUseCase readTeamsUseCase;
    private final CreateTeamUseCase createTeamUseCase;
    private final AddTeamMemberUseCase addTeamMemberUseCase;

    /**
     * Get a list of the existing teams
     * @return
     */
    @GetMapping
    @ApiOperation(value = "Get a paged list of all the teams.",
            tags = {"Teams"},
            authorizations = @Authorization(value = "list",
                    scopes = {@AuthorizationScope(description = "List Teams scope",
                            scope = "teams:list")}))
    public ResponseEntity<Page<TeamDto>> list(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") @Max(100) int size) {
        return ResponseEntity.ok(readTeamsUseCase.getTeams(PageRequest.of(page, size))
                .map(teamDtoMapper::toDto));
    }

    /**
     * Create an empty team
     * @return
     */
    @PostMapping("/{name}")
    @ApiOperation(value = "Create a Team with a name. Project name is unique",
                 tags = {"Teams"},
    authorizations = @Authorization(value = "create",
            scopes = {@AuthorizationScope(description = "Create Teams scope",
                    scope = "teams:create")}))
    public ResponseEntity<TeamDto> create(@PathVariable String name,
                                         @ApiIgnore Principal principal) {

        return ResponseEntity.ok(teamDtoMapper.toDto(createTeamUseCase.createTeam(new TeamCommand(name))));
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "Get a Team by its name together with the list of its members",
            tags = {"Teams"},
            authorizations = @Authorization(value = "getTeamsDetails",
                    scopes = {@AuthorizationScope(description = "Get Team details scope",
                            scope = "teams:view-details")}))
    public ResponseEntity<TeamDetailsDto> getDetails(@PathVariable String name) {
        return ResponseEntity.ok(readTeamsUseCase.getTeamDetails(name)
                .map(teamDtoMapper::toTeamDetailsDto)
                .orElse(new TeamDetailsDto()));
    }

    @PutMapping(path = "/member/add")
    @ApiOperation(value = "Add a Member to a Team.",
            tags = {"Teams"},
            authorizations = @Authorization(value = "add-member",
                    scopes = {@AuthorizationScope(description = "Add Member to Team scope",
                            scope = "teams:add-member")}))
    public ResponseEntity<Void> addTeamMember(
            @Valid @RequestBody TeamMemberCommand command,
            @ApiIgnore Principal principal) {

        addTeamMemberUseCase.addTeamMember(
                new TeamMemberCommand(
                        command.getTeamId(),
                        command.getEmployeeId(),
                        command.getTeamEmail()
                ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
