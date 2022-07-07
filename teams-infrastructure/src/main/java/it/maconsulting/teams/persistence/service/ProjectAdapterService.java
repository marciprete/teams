package it.maconsulting.teams.persistence.service;

import it.maconsulting.microkernel.annotations.PersistenceAdapter;
import it.maconsulting.microkernel.exceptions.DomainException;
import it.maconsulting.teams.application.project.port.out.CreateProjectPort;
import it.maconsulting.teams.application.project.port.out.ModifyProjectPort;
import it.maconsulting.teams.application.project.port.out.ReadProjectPort;
import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.project.Project;
import it.maconsulting.teams.persistence.jpa.entity.EmployeeEntity;
import it.maconsulting.teams.persistence.jpa.entity.ProjectEntity;
import it.maconsulting.teams.persistence.jpa.entity.ProjectMemberEntity;
import it.maconsulting.teams.persistence.jpa.repository.EmployeeJpaRepository;
import it.maconsulting.teams.persistence.jpa.repository.ProjectJpaRepository;
import it.maconsulting.teams.persistence.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class ProjectAdapterService implements ReadProjectPort,
        ModifyProjectPort,
        CreateProjectPort {

    private final ProjectJpaRepository projectJpaRepository;
    private final EmployeeJpaRepository employeeJpaRepository;
    private final ProjectMapper projectMapper;

    @Override
    public Project save(Project project) {
        ProjectEntity saved = projectJpaRepository.save(projectMapper.toEntity(project));
        return projectMapper.toDomain(saved);
    }

    /**
     * This method avoids the "collection overwrite anti-pattern", and uses the best practice of the
     * collection update, as described in detail here: https://vladmihalcea.com/merge-entity-collections-jpa-hibernate/
     *
     * @param project
     * @return
     */
    @Override
    public Project update(Project project) {
        UUID projecId = project.getId().map(Project.ProjectId::getValue).orElseThrow();
        ProjectEntity old = projectJpaRepository.fetchProjectWithMembersById(projecId)
                .orElseThrow();

        ProjectEntity mapped = projectMapper.toEntity(project);
        mapped = rehidrate(mapped, project);
        Set<ProjectMemberEntity> members = mapped.getMembers();

        old.setName(mapped.getName());

        Set<ProjectMemberEntity> removedMembers = new HashSet<>(old.getMembers());
        removedMembers.removeAll(mapped.getMembers());
        removedMembers.forEach(it -> old.removeMember(it.getMember()));

        Set<ProjectMemberEntity> newMembers = new HashSet<>(members);
        newMembers.removeAll(old.getMembers());
        members.removeAll(newMembers);
        newMembers.forEach(it -> old.addMember(it.getMember(), it.getRole()));

        return projectMapper.toDomain(
                projectJpaRepository.save(old)
        );
    }

    private ProjectEntity rehidrate(ProjectEntity entity, Project domain) {
        List<UUID> membersId = domain.getMembers().stream().map(Project.Member::getEmployeeId)
                .map(Employee.EmployeeId::getValue)
                .collect(Collectors.toList());
        List<EmployeeEntity> members = employeeJpaRepository.findAllByIdIn(membersId);
        Map<UUID, EmployeeEntity> memberEntityMap = new HashMap<>();
        if(members.size()!=membersId.size()) throw new DomainException("Cannot find all the members");
        entity.setMembers(new HashSet<>(members.size()));
        members.forEach(member -> memberEntityMap.put(member.getId(), member));
        domain.getMembers().forEach(member ->
                entity.addMember(memberEntityMap.get(member.getEmployeeId().getValue()), member.getProjectRole().name())
        );
        return entity;
    }

    @Override
    public Optional<Project> fetchProjectWithMembersById(Project.ProjectId projectId) {
        return projectJpaRepository.fetchProjectWithMembersById(projectId.getValue()).map(
                it -> projectMapper.toDomain(it, it.getMembers())
        );
    }

    @Override
    public Optional<Project> findProjectByName(String name) {
        return projectJpaRepository.findByName(name).map(projectMapper::toDomain);
    }

    @Override
    public Page<Project> findAll(Pageable pageRequest) {
        return projectJpaRepository.findAll(pageRequest).map(projectMapper::toDomain);
    }
}
