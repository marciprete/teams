package it.maconsulting.teams.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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
    private MemberEntity member;

    private String role;

    public ProjectMemberEntity(ProjectEntity project, MemberEntity member, String role) {
        this.project = project;
        this.member = member;
        this.role = role;
        this.id = new ProjectMemberId(project.getId(), member.getId());
    }
}
