package it.maconsulting.teams.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private final MemberId id;
    private final String name;
    private final String surname;
    private final String email;

    public Optional<MemberId> getId() {
        return Optional.ofNullable(id);
    }

    @Value
    @ValueObject
    public static class MemberId {
        UUID value;
    }
}
