package it.maconsulting.teams.persistence.service;

import it.maconsulting.microkernel.annotations.PersistenceAdapter;
import it.maconsulting.microkernel.exceptions.DomainException;
import it.maconsulting.teams.application.employee.port.out.CreateEmployeePort;
import it.maconsulting.teams.application.employee.port.out.ReadEmployeePort;
import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.persistence.jpa.entity.EmployeeEntity;
import it.maconsulting.teams.persistence.jpa.repository.EmployeeJpaRepository;
import it.maconsulting.teams.persistence.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class EmployeeAdapterService implements ReadEmployeePort,
        CreateEmployeePort {

    private final EmployeeJpaRepository employeeJpaRepository;
    private final EmployeeMapper mapper = new EmployeeMapper();

    @Override
    public Optional<Employee> findById(Employee.EmployeeId id) {
        return employeeJpaRepository.findById(id.getValue()).map(mapper::toDomain);
    }

    @Override
    public Page<Employee> findAll(Pageable pageRequest) {
        Page<EmployeeEntity> all = employeeJpaRepository.findAll(pageRequest);
        return all.map(mapper::toDomain);
    }

    @Override
    public Employee create(Employee employee) {
        Optional<EmployeeEntity> byEmail = employeeJpaRepository.findByEmail(employee.getEmail());
        if(byEmail.isPresent()) {
            throw new DomainException("User Already Exists");
        }
        return mapper.toDomain(employeeJpaRepository.save(mapper.toEntity(employee)));
    }
}
