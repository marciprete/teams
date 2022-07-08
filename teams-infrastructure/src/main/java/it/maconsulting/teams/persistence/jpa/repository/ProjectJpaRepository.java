package it.maconsulting.teams.persistence.jpa.repository;

import it.maconsulting.teams.persistence.jpa.entity.ProjectEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ProjectJpaRepository extends PagingAndSortingRepository<ProjectEntity, UUID> {

    @Query("select p from ProjectEntity p " +
            "left outer join fetch p.members " +
            "where p.id = :uuid")
    Optional<ProjectEntity> fetchProjectWithMembersById(@Param("uuid") UUID uuid);

    Optional<ProjectEntity> findByName(String name);
    @Query("select p from ProjectEntity p " +
            "left outer join fetch p.members " +
            "where p.name = :name")
    Optional<ProjectEntity> fetchProjectWithMembersByName(String name);
}
