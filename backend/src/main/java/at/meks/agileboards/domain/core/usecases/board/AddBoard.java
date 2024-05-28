package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.Board;
import at.meks.agileboards.domain.core.model.board.BoardCreated;
import at.meks.agileboards.domain.core.model.board.BoardId;
import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import lombok.RequiredArgsConstructor;

import static at.meks.validation.args.ArgValidator.validate;

@RequiredArgsConstructor
public final class AddBoard {

    private final BoardRepository boardRepository;

    private final TeamRepository teamRepository;

    public Board add(TeamId teamId, BoardName boardName) throws BoardAlreadyExists {
        validate().that(teamId).withArgumentName("teamId").isNotNull();
        validate().that(boardName).withArgumentName("boardName").isNotNull();
        if (!teamRepository.exists(teamId)) {
            throw new IllegalStateException("Team with teamId " + teamId.uuid() + " does not exist.");
        }
        if (boardRepository.exists(teamId, boardName)) {
            throw new BoardAlreadyExists(teamId, boardName);
        }
        BoardCreated boardCreated = new BoardCreated(BoardId.random(), teamId, boardName);
        boardRepository.add(boardCreated);
        return new Board(boardCreated);
    }

}
