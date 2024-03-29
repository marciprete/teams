package it.maconsulting.teams.application.team.port.in.command;

import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.team.Team;
import lombok.Value;
import org.jmolecules.ddd.annotation.ValueObject;

import javax.validation.constraints.NotNull;


@Value
@ValueObject
public class TeamCommand {
    @NotNull
    private final String name;
}
