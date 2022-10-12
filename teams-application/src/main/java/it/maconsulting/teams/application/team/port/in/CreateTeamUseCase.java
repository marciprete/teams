package it.maconsulting.teams.application.team.port.in;

import it.maconsulting.teams.application.team.port.in.command.TeamCommand;
import it.maconsulting.teams.domain.model.team.Team;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface CreateTeamUseCase {

    Team createTeam(TeamCommand command);

}
