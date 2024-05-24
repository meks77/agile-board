package at.meks.agileboards.domain.core.usecases.team;

import at.meks.agileboards.domain.core.model.team.TeamName;

public class TeamAlreadyExistsException extends Exception {

    public TeamAlreadyExistsException(TeamName teamName) {
        super(String.format("Team with teamName %s exists already.", teamName.text()));
    }
}
