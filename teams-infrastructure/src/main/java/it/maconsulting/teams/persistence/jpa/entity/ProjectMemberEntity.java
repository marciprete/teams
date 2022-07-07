package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Data
@NoArgsConstructor
@Table(name = "project_member")
@Entity(name = "ProjectMember")
public class ProjectMemberEntity {

    @EmbeddedId
    private ProjectMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    private EmployeeEntity member;

    private String role;

    public ProjectMemberEntity(ProjectEntity project, EmployeeEntity member, String role) {
        this.project = project;
        this.member = member;
        this.role = role;
        this.id = new ProjectMemberId(project.getId(), member.getId());
    }
}
