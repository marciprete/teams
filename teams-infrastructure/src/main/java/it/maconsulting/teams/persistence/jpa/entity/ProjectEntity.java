package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
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

//    @OneToMany(
//            mappedBy = "project",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<ProjectMemberEntity> members;

}
