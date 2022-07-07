package it.maconsulting.teams.presentation.controller.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private String name;
    private String surname;
    private String email;
}
