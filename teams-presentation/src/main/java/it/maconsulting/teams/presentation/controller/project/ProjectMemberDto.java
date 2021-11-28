package it.maconsulting.teams.presentation.controller.project;

import lombok.Value;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Value
public class ProjectMemberDto {
    private String name;
    private String surname;
    private String role;
}
