package it.maconsulting.teams.presentation.controller.member;

import it.maconsulting.teams.domain.model.Member;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Component
public class MemberDtoMapper implements Function<Member, MemberDto> {
    @Override
    public MemberDto apply(Member member) {
        return new MemberDto(member.getName(),
                member.getSurname(),
                member.getEmail());
    }
}
