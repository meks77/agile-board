package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;

public class AddTeam {

    private final TeamRepository teamRepository;

    public AddTeam(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team addTeam(TeamName teamName) throws TeamAlreadyExistsException {
        if (teamRepository.exists(teamName)) {
            throw new TeamAlreadyExistsException(teamName);
        }
        var teamCreated = new TeamCreated(TeamId.random(), teamName);
        var team = new Team(teamCreated);
        teamRepository.add(teamCreated);

        return team;
    }
}
