package it.maconsulting.teams.application.project.port.out;

import it.maconsulting.teams.domain.model.project.Project;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface CreateProjectPort {
    Project save(Project project);
}
