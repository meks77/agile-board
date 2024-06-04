package at.meks.agileboards.domain.core.model.team;

import at.meks.agileboards.domain.core.model.EventId;
import at.meks.agileboards.domain.core.model.Timestamp;

public record TeamCreated(EventId id, Timestamp created, TeamId teamId, TeamName teamName) implements TeamEvent {

    public TeamCreated(TeamId teamId, TeamName teamName) {
        this(EventId.random(), Timestamp.now(), teamId, teamName);
    }

}
