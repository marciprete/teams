package it.maconsulting.teams.persistence.jpa.repository;

import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.persistence.jpa.entity.MemberEntity;
import it.maconsulting.teams.persistence.jpa.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;
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
public interface MemberJpaRepository extends PagingAndSortingRepository<MemberEntity, UUID> {

    List<MemberEntity> findAllByIdIn(List<UUID> membersId);
}
