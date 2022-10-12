package it.maconsulting.teams.application.team.port.out;

import it.maconsulting.teams.domain.model.team.Team;

public interface CreateTeamPort {

    Team save(Team team);
}
