package it.maconsulting.teams.application.service.member;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.teams.application.member.port.in.CreateMemberUseCase;
import it.maconsulting.teams.application.member.port.in.request.CreateMemberCommand;
import it.maconsulting.teams.application.member.port.out.CreateMemberPort;
import it.maconsulting.teams.domain.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateMemberService implements CreateMemberUseCase {

    private final CreateMemberPort createMemberPort;

    @Override
    public void createMember(CreateMemberCommand command) {
        createMemberPort.create(Member.builder()
                        .id(UUID.randomUUID())
                        .name(command.getName())
                        .surname(command.getSurname())
                        .email(command.getEmail())
                .build());
    }
}
