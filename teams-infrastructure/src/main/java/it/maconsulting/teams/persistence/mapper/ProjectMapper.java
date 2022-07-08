package it.maconsulting.teams.persistence.mapper;

import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.employee.EmployeeProjectRoleEnum;
import it.maconsulting.teams.domain.model.project.Project;
import it.maconsulting.teams.persistence.jpa.entity.ProjectEntity;
import it.maconsulting.teams.persistence.jpa.entity.ProjectMemberEntity;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Component
public class ProjectMapper {

    public Project toDomain(ProjectEntity entity) {
        return toDomain(entity, Collections.emptySet());
    }

    public Project toDomain(ProjectEntity entity,
                     Set<ProjectMemberEntity> memberEntities) {
        Set<Project.Member> members = new HashSet<>();
        if(memberEntities != null) {
            memberEntities.forEach(me -> members.add(new Project.Member(
                    new Employee.EmployeeId(me.getId().getMemberId()),
                    me.getMember().getName() + " " + me.getMember().getSurname(),
                    EmployeeProjectRoleEnum.valueOf(me.getRole())
                    )
            ));
        }
        return Project.withId(new Project.ProjectId(entity.getId()),
                entity.getName(),
                members);
    }

    public ProjectEntity toEntity(Project domain) {
        ProjectEntity entity = new ProjectEntity();
        UUID projectId = domain.getId().map(Project.ProjectId::getValue).orElse(null);
        entity.setId(projectId);
        entity.setName(domain.getName());
        return entity;
    }
}
