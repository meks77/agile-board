package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;

import static at.meks.validation.args.ArgValidator.validate;

public record BoardCreated(BoardId id, TeamId teamId, BoardName name) {

    public BoardCreated {
        validate().that(id).withArgumentName("id").isNotNull();
        validate().that(teamId).withArgumentName("teamId").isNotNull();
        validate().that(name).withArgumentName("name").isNotNull();
    }

}
