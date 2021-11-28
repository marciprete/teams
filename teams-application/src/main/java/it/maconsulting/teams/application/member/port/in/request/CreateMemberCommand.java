package it.maconsulting.teams.application.member.port.in.request;

import it.maconsulting.microkernel.validation.SelfValidating;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
public class CreateMemberCommand extends SelfValidating<CreateMemberCommand> {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    public CreateMemberCommand(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.validateSelf();
    }
}
