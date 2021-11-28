package it.maconsulting.teams.application.service.member;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.teams.application.member.port.in.ReadMemberUseCase;
import it.maconsulting.teams.application.member.port.out.ReadMemberPort;
import it.maconsulting.teams.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@UseCase
@RequiredArgsConstructor
public class ReadMemberService implements ReadMemberUseCase {

    private final ReadMemberPort readMemberPort;

    @Override
    public Optional<Member> findMemberById(Member.MemberId id) {
        return Optional.empty();
    }

    @Override
    public Page<Member> listMembers(Pageable pageRequest) {
        return readMemberPort.findAll(pageRequest);
    }
}
