package it.maconsulting.teams.persistence.jpa.repository;

import it.maconsulting.teams.persistence.jpa.entity.TeamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface TeamJpaRepository extends PagingAndSortingRepository<TeamEntity, UUID> {

    Optional<TeamEntity> findByName(String name);

    @Query("from TeamEntity t " +
            "left join fetch t.members " +
            "where t.id = :teamId")
    Optional<TeamEntity> fetchTeamWithMembersById(UUID teamId);

    @Query("from TeamEntity t " +
            "left join fetch t.members " +
            "where t.name = :name")
    Optional<TeamEntity> fetchTeamWithMembersByName(String name);
}
