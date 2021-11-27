package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Data
@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    private UUID id;

    private String name;

    @OneToMany
    private Set<ProjectMemberEntity> members;
}
