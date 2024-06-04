package at.meks.agileboards.domain.core.model.board;

import at.meks.agileboards.domain.core.model.Timestamp;
import at.meks.agileboards.domain.core.model.team.TeamId;

import static at.meks.validation.args.ArgValidator.validate;

public record BoardCreated(Timestamp created, BoardId id, TeamId teamId, BoardName name) {

    public BoardCreated {
        validate().that(id).withArgumentName("id").isNotNull();
        validate().that(teamId).withArgumentName("teamId").isNotNull();
        validate().that(name).withArgumentName("name").isNotNull();
    }

    public BoardCreated(BoardId id, TeamId teamId, BoardName name) {
        this(Timestamp.now(), id, teamId, name);
    }

}
