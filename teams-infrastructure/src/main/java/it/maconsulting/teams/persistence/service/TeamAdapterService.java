package it.maconsulting.teams.persistence.service;

import it.maconsulting.microkernel.annotations.PersistenceAdapter;
import it.maconsulting.microkernel.exceptions.EntityNotFoundException;
import it.maconsulting.teams.application.team.port.out.CreateTeamPort;
import it.maconsulting.teams.application.team.port.out.ModifyTeamPort;
import it.maconsulting.teams.application.team.port.out.ReadTeamsPort;
import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.team.Team;
import it.maconsulting.teams.persistence.jpa.entity.EmployeeEntity;
import it.maconsulting.teams.persistence.jpa.entity.TeamEntity;
import it.maconsulting.teams.persistence.jpa.repository.EmployeeJpaRepository;
import it.maconsulting.teams.persistence.jpa.repository.TeamJpaRepository;
import it.maconsulting.teams.persistence.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

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
    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Team save(Team team) {
        return teamMapper.toDomain(teamJpaRepository.save(teamMapper.toEntity(team)));
    }

    @Override
    public Team update(Team team) {
        return null;
    }

    @Override
    public Team addMember(Team.TeamId teamId, Employee.EmployeeId employeeId, String email) {
        TeamEntity entity = teamJpaRepository.ftchTeamWithMembersById(teamId.getValue()).orElseThrow(
                () -> new EntityNotFoundException("Team", teamId.toString())
        );
        EmployeeEntity employee = employeeJpaRepository.findById(employeeId.getValue()).orElseThrow(
                () -> new EntityNotFoundException("Employee", employeeId.toString())
        );
        entity.addMember(employee, email);
        return teamMapper.toDomain(
                teamJpaRepository.save(entity)
        );
    }

    @Override
    public Team rename(String name) {
        return null;
    }

    @Override
    public Page<Team> list(Pageable pageRequest) {
        return teamJpaRepository.findAll(pageRequest).map(teamMapper::toDomain);
    }

    @Override
    public Optional<Team> findByName(String name) {
        return teamJpaRepository.findByName(name).map(teamMapper::toDomain);
    }

    @Override
    public Optional<Team> fetchTeamWithMembersById(Team.TeamId teamId) {
        return teamJpaRepository.ftchTeamWithMembersById(teamId.getValue())
                .map(it -> teamMapper.toDomain(it, it.getMembers()));
    }
}
