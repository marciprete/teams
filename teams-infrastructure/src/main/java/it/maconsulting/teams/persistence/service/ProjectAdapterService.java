package it.maconsulting.teams.persistence.service;

import it.maconsulting.teams.application.project.port.out.ModifyProjectPort;
import it.maconsulting.teams.application.project.port.out.ReadProjectPort;
import it.maconsulting.teams.domain.model.Project;
import it.maconsulting.teams.persistence.jpa.repository.ProjectJpaRepository;
import it.maconsulting.teams.persistence.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@RequiredArgsConstructor
public class ProjectAdapterService implements ReadProjectPort,
                                            ModifyProjectPort {

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectMapper projectMapper;

    @Override
    public void save(Project project) {
        projectJpaRepository.save(null);
    }

    @Override
    public Optional<Project> fetchProjectWithMembersById(Project.ProjectId projectId) {
        return projectJpaRepository.fetchProjectWithMembersById(projectId.getValue()).map(
                it -> projectMapper.toDomain(it, it.getMembers())
        );
    }
}
