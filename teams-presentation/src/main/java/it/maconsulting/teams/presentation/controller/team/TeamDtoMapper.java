package it.maconsulting.teams.presentation.controller.team;

import it.maconsulting.teams.domain.model.team.Team;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public class TeamDtoMapper {

    public TeamDto toDto(Team domain) {
        return new TeamDto(domain.getId().get().getValue(),
                domain.getName());
    }

//    public ProjectDetailsDto toProjectDetailsDto(Project domain) {
//        return new ProjectDetailsDto(
//                domain.getId().get().getValue(),
//                domain.getName(),
//                domain.getMembers().stream().map(member -> new ProjectMemberDto(
//                        member.getEmployeeId().getValue(),
//                        member.getFullName(),
//                        member.getProjectRole().name()
//                )).collect(Collectors.toList()));
//    }
}
