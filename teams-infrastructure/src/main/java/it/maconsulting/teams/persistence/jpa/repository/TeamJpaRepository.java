package it.maconsulting.teams.persistence.jpa.repository;

import it.maconsulting.teams.persistence.jpa.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface TeamJpaRepository extends CrudRepository<TeamEntity, UUID> {

    Optional<TeamEntity> findByName(String name);
}
