package it.maconsulting.teams.application.project.port.in;

import it.maconsulting.teams.application.project.port.in.command.ProjectMemberCommand;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ChangeProjectRoleUseCase {

    void changeProjectRole(ProjectMemberCommand command);
}
