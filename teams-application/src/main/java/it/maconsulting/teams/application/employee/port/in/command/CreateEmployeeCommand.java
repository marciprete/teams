package it.maconsulting.teams.application.employee.port.in.command;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
public class CreateEmployeeCommand {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Email
    @NotNull
    private String email;

    public CreateEmployeeCommand(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email.toLowerCase();
    }
}
