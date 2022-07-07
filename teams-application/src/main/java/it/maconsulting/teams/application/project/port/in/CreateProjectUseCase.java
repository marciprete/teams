package it.maconsulting.teams.application.project.port.in;

import it.maconsulting.teams.domain.model.project.Project;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface CreateProjectUseCase {

    Project createProject(String name);
}
