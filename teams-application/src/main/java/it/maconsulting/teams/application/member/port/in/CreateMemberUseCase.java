package it.maconsulting.teams.application.member.port.in;

import it.maconsulting.teams.application.member.port.in.request.CreateMemberCommand;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface CreateMemberUseCase {

    void createMember(CreateMemberCommand command);

}
