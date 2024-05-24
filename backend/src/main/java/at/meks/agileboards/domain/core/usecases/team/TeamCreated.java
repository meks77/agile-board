package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;

public record TeamCreated(TeamId teamId, TeamName teamName) {
}
