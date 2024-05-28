package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.Board;
import at.meks.agileboards.domain.core.model.board.BoardCreated;
import at.meks.agileboards.domain.core.model.board.BoardId;
import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;

import java.util.Collection;
import java.util.Optional;

public interface BoardRepository {
    void add(BoardCreated boardCreated);

    boolean exists(TeamId teamId, BoardName boardName);

    Collection<Board> getAll();

    Optional<Board> get(BoardId boardId);
}
