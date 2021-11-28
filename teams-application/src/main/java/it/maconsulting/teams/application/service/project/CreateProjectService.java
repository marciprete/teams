package it.maconsulting.teams.application.service.project;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.teams.application.project.port.in.CreateProjectUseCase;
import it.maconsulting.teams.application.project.port.in.ReadProjectUseCase;
import it.maconsulting.teams.application.project.port.out.CreateProjectPort;
import it.maconsulting.teams.application.project.port.out.ReadProjectPort;
import it.maconsulting.teams.domain.model.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateProjectService implements CreateProjectUseCase {

    private final CreateProjectPort createProjectPort;

    @Override
    public Project createProject(String name) {
        Project project = Project.builder()
                .id(new Project.ProjectId(UUID.randomUUID()))
                .name(name)
                .build();
        return createProjectPort.save(project);
    }
}
