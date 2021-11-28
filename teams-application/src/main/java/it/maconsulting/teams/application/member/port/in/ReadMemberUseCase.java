package it.maconsulting.teams.application.member.port.in;

import it.maconsulting.teams.domain.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ReadMemberUseCase {

    Optional<Member> findMemberById(Member.MemberId id);

    Page<Member> listMembers(Pageable pageRequest);

}
