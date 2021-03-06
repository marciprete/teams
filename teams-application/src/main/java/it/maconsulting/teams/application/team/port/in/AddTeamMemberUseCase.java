package it.maconsulting.teams.application.team.port.in;

import it.maconsulting.teams.application.project.port.in.command.AddProjectMemberCommand;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface AddTeamMemberUseCase {

    void addTeamMember(AddProjectMemberCommand command);

}
