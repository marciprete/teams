package it.maconsulting.teams.presentation.controller.project;

import it.maconsulting.teams.domain.model.project.Project;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public class ProjectDtoMapper {

    public ProjectDto toDto(Project domain) {
        return new ProjectDto(domain.getId().get().getValue(),
                domain.getName());
    }
}
