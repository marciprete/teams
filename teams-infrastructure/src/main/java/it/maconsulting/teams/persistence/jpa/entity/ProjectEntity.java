package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Data
@Entity
@Table(name = "project")
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class ProjectEntity {

    @Id
    private UUID id;

    @NaturalId
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "project",
            cascade = {CascadeType.ALL}
    )
    private Set<ProjectMemberEntity> members;

    public void addMember(EmployeeEntity member, String role) {
        ProjectMemberEntity projectMember = new ProjectMemberEntity(this, member, role);
        members.add(projectMember);
        member.getProjects().add(projectMember);
    }

    public void removeMember(EmployeeEntity member) {
        for (Iterator<ProjectMemberEntity> iterator = members.iterator();
             iterator.hasNext(); ) {
            ProjectMemberEntity projectMember = iterator.next();

            if (projectMember.getProject().equals(this) &&
                    projectMember.getMember().equals(member)) {
                iterator.remove();
                projectMember.getProject().getMembers().remove(projectMember);
                projectMember.setProject(null);
                projectMember.setMember(null);
            }
        }
    }
}
