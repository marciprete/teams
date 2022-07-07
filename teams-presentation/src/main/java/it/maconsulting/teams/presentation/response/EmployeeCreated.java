package it.maconsulting.teams.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreated {
    private UUID id;
    private String name;
    private String surname;
    private String email;
}
