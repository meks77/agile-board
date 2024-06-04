package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamCreated;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;

import java.util.Collection;

public interface TeamRepository {
    boolean exists(TeamName newTeamName);

    void add(TeamCreated teamCreated);

    boolean exists(TeamId existingTeamId);

    Collection<Team> getAll();

    Team get(TeamId teamId);
}
