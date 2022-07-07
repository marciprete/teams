package it.maconsulting.teams.persistence.mapper;

import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.project.Project;
import it.maconsulting.teams.persistence.jpa.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Component
public class EmployeeMapper {

    public Employee toDomain(EmployeeEntity entity) {
        Set<Project.Member> members = new HashSet<>();
        return Employee.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .build();
    }

    public EmployeeEntity toEntity(Employee employee) {
        return new EmployeeEntity(
                employee.getId().map(Employee.EmployeeId::getValue).orElse(null),
                employee.getName(),
                employee.getSurname(),
                employee.getEmail(),
                null,
                null
        );
    }
}
