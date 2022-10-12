package it.maconsulting.teams.application.service.employee;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.teams.application.employee.port.in.CreateEmployeeUseCase;
import it.maconsulting.teams.application.employee.port.in.command.CreateEmployeeCommand;
import it.maconsulting.teams.application.employee.port.out.CreateEmployeePort;
import it.maconsulting.teams.domain.model.employee.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateEmployeeService implements CreateEmployeeUseCase {

    private final CreateEmployeePort createEmployeePort;

    @Override
    public Employee createEmployee(CreateEmployeeCommand command) {
        return createEmployeePort.create(Employee.builder()
                        .id(new Employee.EmployeeId(UUID.randomUUID()))
                        .name(command.getName())
                        .surname(command.getSurname())
                        .email(command.getEmail())
                .build());
    }
}
