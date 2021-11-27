package it.maconsulting.teams.persistence.mapper;

import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.domain.model.Project;
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
public class ProjectMapper {

    public Project toDomain(ProjectEntity entity,
                     Set<ProjectMemberEntity> memberEntities) {
        Set<Project.ProjectMember> members = new HashSet<>();
        if(memberEntities != null) {
            memberEntities.forEach(me -> members.add(new Project.ProjectMember(
                    new Member.MemberId(me.getId().getMemberId()),
                    me.getRole()
                    )
            ));
        }
        return Project.withId(new Project.ProjectId(entity.getId()),
                entity.getName(),
                members);
    }
}
