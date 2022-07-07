package it.maconsulting.teams.application.employee.port.in.command;

import it.maconsulting.microkernel.validation.SelfValidating;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
public class CreateEmployeeCommand extends SelfValidating<CreateEmployeeCommand> {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    public CreateEmployeeCommand(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.validateSelf();
    }
}
