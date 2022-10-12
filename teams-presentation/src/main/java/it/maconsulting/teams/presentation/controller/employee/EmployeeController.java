package it.maconsulting.teams.presentation.controller.employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import it.maconsulting.microkernel.annotations.WebAdapter;
import it.maconsulting.teams.application.employee.port.in.CreateEmployeeUseCase;
import it.maconsulting.teams.application.employee.port.in.ReadEmployeeUseCase;
import it.maconsulting.teams.application.employee.port.in.command.CreateEmployeeCommand;
import it.maconsulting.teams.domain.model.employee.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.security.Principal;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@Api(tags = {"Employees"})
@RequestMapping("/employees")
public class EmployeeController {
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final ReadEmployeeUseCase readEmployeeUseCase;
    private final EmployeeDtoMapper employeeDtoMapper;

    @PostMapping()
    @ApiOperation(value = "Add a new employee.",
            tags = {"Employees"},
            authorizations = @Authorization(value = "createEmployee",
                    scopes = {@AuthorizationScope(description = "Create Employees scope",
                            scope = "employee:create")}))
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody CreateEmployeeCommand command,
                                               @ApiIgnore Principal principal) {
        return new ResponseEntity(employeeDtoMapper.toEmployeeCreated(createEmployeeUseCase.createEmployee(command)),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an employee by id.",
            tags = {"Employees"},
            authorizations = @Authorization(value = "find",
                    scopes = {@AuthorizationScope(description = "Read Employee scope",
                            scope = "employee:view-details")}))
    public ResponseEntity<EmployeeDto> find(@PathVariable UUID id) {
        return ResponseEntity.ok(readEmployeeUseCase.findEmployeeById(new Employee.EmployeeId(id)).map(employeeDtoMapper).orElse(null));
    }

    @GetMapping()
    @ApiOperation(value = "Get a paged list of all employees.",
            tags = {"Employees"},
            authorizations = @Authorization(value = "list",
                    scopes = {@AuthorizationScope(description = "List Employees scope",
                            scope = "employee:list")}))
    public ResponseEntity<Page<EmployeeDto>> list(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") @Max(100) int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Employee> memberPage = readEmployeeUseCase.listEmployees(pageRequest);
        Page<EmployeeDto> memberDtos = new PageImpl<>(
                memberPage.getContent().stream().map(employeeDtoMapper).collect(Collectors.toList()),
                pageRequest,
                memberPage.getTotalElements()
        );
        return ResponseEntity.ok(memberDtos);
    }
}
