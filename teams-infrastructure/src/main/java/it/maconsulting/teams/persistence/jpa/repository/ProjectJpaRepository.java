package it.maconsulting.teams.persistence.jpa.repository;

import it.maconsulting.teams.persistence.jpa.entity.ProjectEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ProjectJpaRepository extends CrudRepository<ProjectEntity, UUID> {

    @Query("select p from ProjectEntity p " +
            "inner join fetch p.members " +
            "where p.id = :uuid")
    Optional<ProjectEntity> fetchProjectWithMembersById(@Param("uuid") UUID uuid);
}
