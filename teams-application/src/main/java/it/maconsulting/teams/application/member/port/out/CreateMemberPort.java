package it.maconsulting.teams.application.member.port.out;

import it.maconsulting.teams.domain.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface CreateMemberPort {
    Member create(Member member);
}
