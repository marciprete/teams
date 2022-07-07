package it.maconsulting.teams.application.employee.port.out;

import it.maconsulting.teams.domain.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ReadEmployeePort {
    Optional<Employee> findById(Employee.EmployeeId id);

    Page<Employee> findAll(Pageable pageRequest);
}
