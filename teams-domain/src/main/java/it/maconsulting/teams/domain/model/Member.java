package it.maconsulting.teams.domain.model;

import lombok.*;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Entity
public class Member {
    private final MemberId id;
    private final String name;
    private final String surname;
    private final String email;

    public Optional<MemberId> getId() {
        return Optional.ofNullable(id);
    }

    @Builder
    private Member(UUID id, String name, String surname, String email) {
        this.id = new MemberId(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    @Value
    @ValueObject
    public static class MemberId {
        UUID value;
    }
}
