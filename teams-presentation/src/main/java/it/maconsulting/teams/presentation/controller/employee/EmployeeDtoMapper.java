package it.maconsulting.teams.presentation.controller.employee;

import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.presentation.response.EmployeeCreated;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Component
public class EmployeeDtoMapper implements Function<Employee, EmployeeDto> {
    @Override
    public EmployeeDto apply(Employee employee) {
        return new EmployeeDto(
                employee.getId().get().getValue(),
                employee.getName(),
                employee.getSurname(),
                employee.getEmail());
    }

    public EmployeeCreated toEmployeeCreated(Employee domain) {
        return new EmployeeCreated(domain.getId().get().getValue(),
                domain.getName(),
                domain.getSurname(),
                domain.getEmail());
    }
}
