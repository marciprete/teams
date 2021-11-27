package it.maconsulting.teams.persistence.jpa.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Data
@Embeddable
public class ProjectMemberId implements Serializable {

    private UUID projectId;

    private UUID memberId;

}
