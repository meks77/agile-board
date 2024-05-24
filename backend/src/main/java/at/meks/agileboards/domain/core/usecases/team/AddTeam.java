package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.team.Team;
import at.meks.agileboards.domain.core.model.team.TeamCreated;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.model.team.TeamName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddTeam {

    private final TeamRepository teamRepository;

    public Team addTeam(TeamName teamName) throws TeamAlreadyExistsException {
        if (teamRepository.exists(teamName)) {
            throw new TeamAlreadyExistsException(teamName);
        }
        var teamCreated = new TeamCreated(TeamId.random(), teamName);
        teamRepository.add(teamCreated);

        return new Team(teamCreated);
    }

}
