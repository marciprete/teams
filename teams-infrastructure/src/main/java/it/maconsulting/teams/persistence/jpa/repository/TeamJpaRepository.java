package it.maconsulting.teams.persistence.jpa.repository;

import it.maconsulting.teams.domain.model.team.Team;
import it.maconsulting.teams.persistence.jpa.entity.TeamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
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
    Optional<TeamEntity> ftchTeamWithMembersById(UUID teamId);
}
