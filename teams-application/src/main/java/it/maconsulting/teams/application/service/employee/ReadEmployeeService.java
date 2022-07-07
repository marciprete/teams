package it.maconsulting.teams.application.service.employee;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.teams.application.employee.port.in.ReadEmployeeUseCase;
import it.maconsulting.teams.application.employee.port.out.ReadEmployeePort;
import it.maconsulting.teams.domain.model.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@UseCase
@RequiredArgsConstructor
public class ReadEmployeeService implements ReadEmployeeUseCase {

    private final ReadEmployeePort readEmployeePort;

    @Override
    public Optional<Employee> findEmployeeById(Employee.EmployeeId id) {
        return Optional.empty();
    }

    @Override
    public Page<Employee> listEmployees(Pageable pageRequest) {
        return readEmployeePort.findAll(pageRequest);
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return Optional.empty();
    }
}
