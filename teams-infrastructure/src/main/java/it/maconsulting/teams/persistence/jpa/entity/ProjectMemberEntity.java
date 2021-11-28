package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Data
//@Entity
@Table(name = "project")
public class ProjectMemberEntity {

//    @EmbeddedId
//    private ProjectMemberId id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("projectId")
//    private ProjectEntity project;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("memberId")
//    private MemberEntity member;
//
//    private String role;

//    public ProjectMemberEntity(ProjectEntity project, MemberEntity member) {
//        this.project = project;
//        this.member = member;
//        this.id = new ProjectMemberId(project.getId(), member.getId());
//    }
}
