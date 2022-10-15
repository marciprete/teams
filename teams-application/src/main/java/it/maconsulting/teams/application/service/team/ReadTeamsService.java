package it.maconsulting.teams.application.service.team;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.microkernel.exceptions.DomainException;
import it.maconsulting.teams.application.team.port.in.CreateTeamUseCase;
import it.maconsulting.teams.application.team.port.in.ReadTeamsUseCase;
import it.maconsulting.teams.application.team.port.in.command.TeamCommand;
import it.maconsulting.teams.application.team.port.out.CreateTeamPort;
import it.maconsulting.teams.application.team.port.out.ReadTeamsPort;
import it.maconsulting.teams.domain.model.team.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class ReadTeamsService implements ReadTeamsUseCase {

    private final ReadTeamsPort readTeamsPort;

    @Override
    public Page<Team> getTeams(Pageable pageRequest) {
        return readTeamsPort.list(pageRequest);
    }
}
