package it.maconsulting.teams.presentation.controller.project;

import lombok.Value;

import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Value
public class ProjectMemberDto {
    UUID memberId;
    String fullName;
    String role;
}
