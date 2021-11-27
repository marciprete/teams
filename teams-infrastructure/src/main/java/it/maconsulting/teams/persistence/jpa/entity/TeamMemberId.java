package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Data
@Embeddable
public class TeamMemberId implements Serializable {

    private UUID teamId;

    private UUID memberId;

}
