package it.maconsulting.teams.application.team.port.in;

import it.maconsulting.teams.domain.model.team.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ReadTeamsUseCase {
    Page<Team> getTeams(Pageable pageRequest);

    Optional<Team> getTeamDetails(String name);
}
