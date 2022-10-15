package it.maconsulting.teams.application.service.team;

import it.maconsulting.microkernel.annotations.UseCase;
import it.maconsulting.microkernel.exceptions.EntityNotFoundException;
import it.maconsulting.teams.application.employee.port.out.ReadEmployeePort;
import it.maconsulting.teams.application.team.port.in.AddTeamMemberUseCase;
import it.maconsulting.teams.application.team.port.in.command.TeamMemberCommand;
import it.maconsulting.teams.application.team.port.out.ModifyTeamPort;
import it.maconsulting.teams.application.team.port.out.ReadTeamsPort;
import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.team.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class ManageTeamMembersService implements AddTeamMemberUseCase {

    private final ReadEmployeePort readEmployeePort;
    private final ReadTeamsPort readTeamsPort;
    private final ModifyTeamPort modifyTeamPort;

    @Override
    public void addTeamMember(TeamMemberCommand command) {
        Employee employee = readEmployeePort.findById(new Employee.EmployeeId(command.getEmployeeId())).orElseThrow(
                ()-> new EntityNotFoundException("Employee", command.getEmployeeId().toString()));
        Team team = readTeamsPort.fetchTeamWithMembersById(new Team.TeamId(command.getTeamId())).orElseThrow(
                ()-> new EntityNotFoundException("Team", command.getTeamId().toString()));

        team.addTeamMember(employee, command.getTeamEmail());

        modifyTeamPort.addMember(team.getId().get(), employee.getId().get(), command.getTeamEmail());
    }
}
