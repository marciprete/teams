package it.maconsulting.teams.persistence.mapper;

import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.domain.model.Project;
import it.maconsulting.teams.persistence.jpa.entity.MemberEntity;
import it.maconsulting.teams.persistence.jpa.entity.ProjectEntity;
import it.maconsulting.teams.persistence.jpa.entity.ProjectMemberEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Component
public class MemberMapper {

    public Member toDomain(MemberEntity entity) {
        Set<Project.ProjectMember> members = new HashSet<>();
        return Member.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .build();
    }

    public MemberEntity toEntity(Member member) {
        return new MemberEntity(
                member.getId().map(Member.MemberId::getValue).orElse(null),
                member.getName(),
                member.getSurname(),
                member.getEmail()
        );
    }
}
