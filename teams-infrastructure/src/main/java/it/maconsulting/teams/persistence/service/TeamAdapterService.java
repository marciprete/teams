package it.maconsulting.teams.persistence.service;

import it.maconsulting.microkernel.annotations.PersistenceAdapter;
import it.maconsulting.teams.application.team.port.out.CreateTeamPort;
import it.maconsulting.teams.application.team.port.out.ModifyTeamPort;
import it.maconsulting.teams.application.team.port.out.ReadTeamsPort;
import it.maconsulting.teams.domain.model.team.Team;
import it.maconsulting.teams.persistence.jpa.entity.TeamEntity;
import it.maconsulting.teams.persistence.jpa.repository.TeamJpaRepository;
import it.maconsulting.teams.persistence.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class TeamAdapterService implements ReadTeamsPort, CreateTeamPort, ModifyTeamPort {

    private final TeamMapper teamMapper = new TeamMapper();
    private final TeamJpaRepository teamJpaRepository;

    @Override
    public Team save(Team team) {
        return teamMapper.toDomain(teamJpaRepository.save(teamMapper.toEntity(team)));
    }

    @Override
    public Team addMember() {
        return null;
    }

    @Override
    public Team rename(String name) {
        return null;
    }

    @Override
    public Page<Team> list() {
        return null;
    }

    @Override
    public Optional<Team> findByName(String name) {
        return teamJpaRepository.findByName(name).map(teamMapper::toDomain);
    }

    @Override
    public Page<?> getDetails() {
        return null;
    }
}
