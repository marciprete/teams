package it.maconsulting.teams.application.team.port.in;

import it.maconsulting.teams.application.team.port.in.command.TeamMemberCommand;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface AddTeamMemberUseCase {

    void addTeamMember(TeamMemberCommand command);

}
