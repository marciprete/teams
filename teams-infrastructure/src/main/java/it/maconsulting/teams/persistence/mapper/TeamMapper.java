package it.maconsulting.teams.persistence.mapper;

import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.team.Team;
import it.maconsulting.teams.persistence.jpa.entity.TeamEntity;
import it.maconsulting.teams.persistence.jpa.entity.TeamMemberEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Component
public class TeamMapper {

    public Team toDomain(TeamEntity entity) {
        return toDomain(entity, Collections.emptySet());
    }

    public Team toDomain(TeamEntity entity,
                     Set<TeamMemberEntity> memberEntities) {
        Set<Team.TeamMember> members = new HashSet<>();
        if(memberEntities != null) {
            memberEntities.forEach(memberEntity -> members.add(new Team.TeamMember(
                    new Employee.EmployeeId(memberEntity.getId().getMemberId()),
                    memberEntity.getMember().getFullName(),
                    memberEntity.getEmail()
                )
            ));
        }
        return Team.withId(new Team.TeamId(entity.getId()),
                entity.getName(),
                members);
    }

    public TeamEntity toEntity(Team domain) {
        TeamEntity entity = new TeamEntity();
        UUID id = domain.getId().map(Team.TeamId::getValue).orElse(null);
        entity.setId(id);
        entity.setName(domain.getName());
        return entity;
    }
}
