package it.maconsulting.teams.presentation.controller.team;

import it.maconsulting.teams.domain.model.team.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDetailsDto {
    private UUID id;
    private String name;
    private Collection<TeamMemberDto> members;
}
