package it.maconsulting.teams.application.project.port.out;

import it.maconsulting.teams.domain.model.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ReadProjectPort {
    Optional<Project> fetchProjectWithMembersById(Project.ProjectId projectId);

    Optional<Project> findProjectByName(String name);

    Page<Project> findAll (Pageable pageRequest);
}
