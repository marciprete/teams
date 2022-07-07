package it.maconsulting.teams.application.project.port.in;

import it.maconsulting.teams.domain.model.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ReadProjectUseCase {

    Page<Project> list(Pageable pageRequest);

    Optional<Project> readProjectDetails(Project.ProjectId id);

}
