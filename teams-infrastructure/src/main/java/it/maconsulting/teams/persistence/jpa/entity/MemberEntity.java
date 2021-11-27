package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Data
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    private UUID id;

    private String name;

    private String surname;

    private String email;

}
