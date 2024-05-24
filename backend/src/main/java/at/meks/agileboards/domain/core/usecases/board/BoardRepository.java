package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;

public interface BoardRepository {
    void add(BoardCreated boardCreated);

    boolean exists(TeamId teamId, BoardName boardName);
}
