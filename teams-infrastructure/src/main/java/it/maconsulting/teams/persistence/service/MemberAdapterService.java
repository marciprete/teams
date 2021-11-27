package it.maconsulting.teams.persistence.service;

import it.maconsulting.teams.application.member.port.in.ReadMemberPort;
import it.maconsulting.teams.domain.model.Member;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public class MemberAdapterService implements ReadMemberPort {

    //private final FeignMemberRepository feignMemberRepository;

    @Override
    public Optional<Member> findById(Member.MemberId id) {
        return Optional.empty();
    }
}
