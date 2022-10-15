package it.maconsulting.teams.application.team.port.in.command;

import lombok.Value;
import org.jmolecules.ddd.annotation.ValueObject;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Value
@ValueObject
public class TeamMemberCommand {
    @NotNull
    UUID teamId;

    @NotNull
    UUID employeeId;

    @NotNull
    String teamEmail;
}
