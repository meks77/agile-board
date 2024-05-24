package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AddBoard {

    private final BoardRepository boardRepository;

    public Board add(TeamId teamId, BoardName boardName) throws BoardAlreadyExists {
        if (boardRepository.exists(teamId, boardName)) {
            throw new BoardAlreadyExists(teamId, boardName);
        }
        BoardCreated boardCreated = new BoardCreated(BoardId.random(), teamId, boardName);
        boardRepository.add(boardCreated);
        return new Board(boardCreated);
    }

}
