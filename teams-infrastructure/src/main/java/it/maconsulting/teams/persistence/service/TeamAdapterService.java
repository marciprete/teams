package it.maconsulting.teams.persistence.service;

import it.maconsulting.microkernel.annotations.PersistenceAdapter;
import it.maconsulting.teams.persistence.jpa.repository.TeamJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class TeamAdapterService  {

    private final TeamJpaRepository teamJpaRepository;

}
