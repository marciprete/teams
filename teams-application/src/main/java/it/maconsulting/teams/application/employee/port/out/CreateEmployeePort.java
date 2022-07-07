package it.maconsulting.teams.application.employee.port.out;

import it.maconsulting.teams.domain.model.employee.Employee;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface CreateEmployeePort {
    Employee create(Employee employee);
}
