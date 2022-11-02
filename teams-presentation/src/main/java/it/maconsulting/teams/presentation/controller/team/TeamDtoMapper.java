package it.maconsulting.teams.presentation.controller.team;

import it.maconsulting.teams.domain.model.project.Project;
import it.maconsulting.teams.domain.model.team.Team;
import it.maconsulting.teams.presentation.controller.project.ProjectDetailsDto;
import it.maconsulting.teams.presentation.controller.project.ProjectMemberDto;

import java.util.stream.Collectors;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public class TeamDtoMapper {

    public TeamDto toDto(Team domain) {
        return new TeamDto(domain.getId().get().getValue(),
                domain.getName());
    }

    public TeamDetailsDto toTeamDetailsDto(Team domain) {
        return new TeamDetailsDto(

                domain.getId().get().getValue(),
                domain.getName(),
                domain.getTeamMembers().stream().map(member -> new TeamMemberDto(
                        member.getEmployeeId().getValue(),
                        member.getFullName(),
                        member.getEmail()
                )).collect(Collectors.toList()));
    }
}
