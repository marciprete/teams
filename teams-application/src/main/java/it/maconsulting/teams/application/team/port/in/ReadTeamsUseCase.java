package it.maconsulting.teams.application.team.port.in;

import org.springframework.data.domain.Page;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ReadTeamsUseCase {
    Page<String> getTeams();
}
