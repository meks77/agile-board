package at.meks.agileboards.adapters.database;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamCreated;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;

import java.util.HashMap;

public class InMemoryTeamRepository implements TeamRepository {

    HashMap<TeamId, Team> teams = new HashMap<>();

    @Override
    public boolean exists(TeamName newTeamName) {
        return teams.values().stream()
                .anyMatch(team -> team.teamName()
                        .equals(newTeamName));
    }

    @Override
    public void add(TeamCreated teamCreated) {
        var team = new Team(teamCreated);
        teams.put(teamCreated.teamId(), team);
    }

    @Override
    public boolean exists(TeamId existingTeamId) {
        return teams.containsKey(existingTeamId);
    }

}
