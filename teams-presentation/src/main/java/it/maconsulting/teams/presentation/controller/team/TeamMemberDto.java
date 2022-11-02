package it.maconsulting.teams.presentation.controller.team;

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
public class TeamMemberDto {
    private UUID id;
    private String fullName;
    private String email;
}
