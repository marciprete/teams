package it.maconsulting.teams.application.team.port.in.command;

import lombok.Value;
import org.jmolecules.ddd.annotation.ValueObject;

import javax.validation.constraints.NotNull;


@Value
@ValueObject
public class TeamCommand {
    @NotNull
    public String name;
}
