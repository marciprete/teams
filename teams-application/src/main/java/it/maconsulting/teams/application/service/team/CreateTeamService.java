package it.maconsulting.teams.application.service.team;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.microkernel.exceptions.DomainException;
import it.maconsulting.teams.application.team.port.in.CreateTeamUseCase;
import it.maconsulting.teams.application.team.port.in.command.TeamCommand;
import it.maconsulting.teams.application.team.port.out.CreateTeamPort;
import it.maconsulting.teams.application.team.port.out.ReadTeamsPort;
import it.maconsulting.teams.domain.model.team.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateTeamService implements CreateTeamUseCase {

    private final CreateTeamPort createTeamPort;
    private final ReadTeamsPort readTeamsPort;

    @Override
    public Team createTeam(TeamCommand command) {
        readTeamsPort.findByName(command.getName()).ifPresent(val -> {throw new DomainException("Team already exists");});
        return createTeamPort.save(
                Team.withoutId(command.getName(), null)
        );
    }
}
