package it.maconsulting.teams.persistence.service;

import it.maconsulting.microkernel.annotations.PersistenceAdapter;
import it.maconsulting.teams.application.member.port.out.CreateMemberPort;
import it.maconsulting.teams.application.member.port.out.ReadMemberPort;
import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.persistence.jpa.entity.MemberEntity;
import it.maconsulting.teams.persistence.jpa.repository.MemberJpaRepository;
import it.maconsulting.teams.persistence.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class MemberAdapterService implements ReadMemberPort,
        CreateMemberPort {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberMapper mapper = new MemberMapper();

    @Override
    public Optional<Member> findById(Member.MemberId id) {
        return memberJpaRepository.findById(id.getValue()).map(mapper::toDomain);
    }

    @Override
    public Page<Member> findAll(Pageable pageRequest) {
        Page<MemberEntity> all = memberJpaRepository.findAll(pageRequest);
        return all.map(mapper::toDomain);
    }

    @Override
    public Member create(Member member) {
        return mapper.toDomain(memberJpaRepository.save(mapper.toEntity(member)));
    }
}
