package it.maconsulting.teams.application.project.port.in.request;

import it.maconsulting.microkernel.validation.SelfValidating;
import it.maconsulting.teams.domain.model.Member;
import it.maconsulting.teams.domain.model.Project;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Value
@EqualsAndHashCode(callSuper = false)
public class AddProjectMemberCommand extends SelfValidating<AddProjectMemberCommand> {
    @NotNull Project.ProjectId projectId;
    @NotNull Member.MemberId memberId;
    @NotNull String role;

    public AddProjectMemberCommand(Project.ProjectId projectId, Member.MemberId memberId, String role) {
        this.projectId = projectId;
        this.memberId = memberId;
        this.role = role;
        this.validateSelf();
    }
}
