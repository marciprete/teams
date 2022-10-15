package it.maconsulting.teams.application.team.port.out;

import it.maconsulting.teams.domain.model.team.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ReadTeamsPort {

    Page<Team> list(Pageable pageRequest);

    Optional<Team> findByName(String name);

    Optional<Team> fetchTeamWithMembersById(Team.TeamId teamId);
}
