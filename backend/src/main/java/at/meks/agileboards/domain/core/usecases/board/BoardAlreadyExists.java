package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;

public class BoardAlreadyExists extends Exception {

    public BoardAlreadyExists(TeamId teamId, BoardName boardName) {
        super(String.format("Board %s already exists for teamid %s", boardName.text(), teamId.uuid()));
    }
}
