package it.maconsulting.teams.domain.model.employee;

import it.maconsulting.teams.domain.model.team.Team;
import lombok.*;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Optional;
import java.util.UUID;

/**
 * The Employee is the base entity of the system.
 *
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Entity
public class Employee {
    private final EmployeeId id;
    private final String name;
    private final String surname;
    private final String email;

    public Optional<EmployeeId> getId() {
        return Optional.ofNullable(id);
    }

    @Builder
    private Employee(EmployeeId id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /**
     * Creates a {@link Team} entity without an ID. Used to create a new entity that is not yet
     * persisted.
     */
    public static Employee withoutId(
            String name,
            String surname,
            String email) {
        return new Employee(null, name, surname, email);
    }

    /**
     * Creates a {@link Team} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static Employee withId(
            EmployeeId id,
            String name,
            String surname,
            String email) {
        return new Employee(id, name, surname, email);
    }

    @Value
    @ValueObject
    public static class EmployeeId {
        UUID value;
    }
}
