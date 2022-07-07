package it.maconsulting.teams.application.employee.port.in;

import it.maconsulting.teams.domain.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface ReadEmployeeUseCase {

    Optional<Employee> findEmployeeById(Employee.EmployeeId id);
    Optional<Employee> findEmployeeByEmail(String email);

    Page<Employee> listEmployees(Pageable pageRequest);

}
