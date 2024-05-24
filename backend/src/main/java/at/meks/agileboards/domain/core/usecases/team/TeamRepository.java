package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.team.TeamName;

public interface TeamRepository {
    boolean exists(TeamName newTeamName);

    void add(TeamCreated team);

}
