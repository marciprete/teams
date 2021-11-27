package it.maconsulting.teams.application.project.port.in;

import it.maconsulting.teams.application.project.port.in.request.AddProjectMemberCommand;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface AddProjectMemberUseCase {

    void addProjectMember(AddProjectMemberCommand command);
}
