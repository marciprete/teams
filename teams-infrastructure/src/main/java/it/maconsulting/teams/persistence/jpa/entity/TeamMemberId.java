package it.maconsulting.teams.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberId implements Serializable {

    private UUID teamId;

    private UUID memberId;

}
