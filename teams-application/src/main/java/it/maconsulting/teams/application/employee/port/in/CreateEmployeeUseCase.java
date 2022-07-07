package it.maconsulting.teams.application.employee.port.in;

import it.maconsulting.teams.application.employee.port.in.command.CreateEmployeeCommand;
import it.maconsulting.teams.domain.model.employee.Employee;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public interface CreateEmployeeUseCase {

    Employee createEmployee(CreateEmployeeCommand command);

}
