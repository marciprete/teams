package it.maconsulting.teams.persistence.jpa.repository;

import it.maconsulting.teams.persistence.jpa.entity.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Repository
public interface EmployeeJpaRepository extends PagingAndSortingRepository<EmployeeEntity, UUID> {

    List<EmployeeEntity> findAllByIdIn(List<UUID> membersId);

    Optional<EmployeeEntity> findByEmail(String email);
}
