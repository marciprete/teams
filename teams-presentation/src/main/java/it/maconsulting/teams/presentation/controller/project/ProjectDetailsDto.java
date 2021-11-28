package it.maconsulting.teams.presentation.controller.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailsDto {
    private UUID id;
    private String name;
    private List<ProjectMemberDto> members;
}
