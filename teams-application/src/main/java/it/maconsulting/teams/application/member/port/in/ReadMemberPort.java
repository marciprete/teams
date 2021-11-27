package it.maconsulting.teams.application.member.port.in;

import it.maconsulting.teams.domain.model.Member;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ReadMemberPort {
    Optional<Member> findById(Member.MemberId id);
}
