package it.maconsulting.teams.application.team.port.out;

import it.maconsulting.teams.domain.model.team.Team;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ReadTeamsPort {

    Page<Team> list();

    Optional<Team> findByName(String name);

    Page<?> getDetails();
}
