package it.maconsulting.teams.application.team.port.out;

import it.maconsulting.teams.domain.model.employee.Employee;
import it.maconsulting.teams.domain.model.team.Team;

public interface ModifyTeamPort {

    Team update(Team team);

    Team addMember(Team.TeamId teamId, Employee.EmployeeId employeeId, String email);

    Team rename(String name);


}
