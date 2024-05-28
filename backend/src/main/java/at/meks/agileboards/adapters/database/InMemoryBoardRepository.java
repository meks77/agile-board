package at.meks.agileboards.adapters.database;

import at.meks.agileboards.domain.core.model.board.Board;
import at.meks.agileboards.domain.core.model.board.BoardCreated;
import at.meks.agileboards.domain.core.model.board.BoardId;
import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.usecases.board.BoardRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryBoardRepository implements BoardRepository {

    private final Map<BoardId, Board> boards = new HashMap<>();

    @Override
    public void add(BoardCreated boardCreated) {
        boards.put(boardCreated.id(), new Board(boardCreated));
    }

    @Override
    public boolean exists(TeamId teamId, BoardName boardName) {
        return boards.values().stream()
                .filter(board -> board.teamId().equals(teamId))
                .anyMatch(board -> board.name().equals(boardName));
    }

    @Override
    public Collection<Board> getAll() {
        return boards.values();
    }

    @Override
    public Optional<Board> get(BoardId boardId) {
        return Optional.ofNullable(boards.get(boardId));
    }
}
