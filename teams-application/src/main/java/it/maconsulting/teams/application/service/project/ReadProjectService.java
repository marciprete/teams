package it.maconsulting.teams.application.service.project;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.teams.application.project.port.in.ReadProjectUseCase;
import it.maconsulting.teams.application.project.port.out.ReadProjectPort;
import it.maconsulting.teams.domain.model.project.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class ReadProjectService implements ReadProjectUseCase {

    private final ReadProjectPort readProjectPort;

    @Override
    public Page<Project> list(Pageable pageRequest) {
        return readProjectPort.findAll(pageRequest);
    }

    @Override
    public Optional<Project> readProjectDetails(Project.ProjectId id) {
        return Optional.empty();
    }
}
