package it.maconsulting.teams.presentation.controller.project;

import it.maconsulting.teams.domain.model.project.Project;

import java.util.stream.Collectors;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public class ProjectDtoMapper {

    public ProjectDto toDto(Project domain) {
        return new ProjectDto(domain.getId().get().getValue(),
                domain.getName());
    }

    public ProjectDetailsDto toProjectDetailsDto(Project domain) {
        return new ProjectDetailsDto(
                domain.getId().get().getValue(),
                domain.getName(),
                domain.getMembers().stream().map(member -> new ProjectMemberDto(
                        member.getEmployeeId().getValue(),
                        member.getFullName(),
                        member.getProjectRole().name()
                )).collect(Collectors.toList()));
    }
}
