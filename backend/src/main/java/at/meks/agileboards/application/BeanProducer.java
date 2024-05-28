package at.meks.agileboards.application;

import at.meks.agileboards.adapters.database.InMemoryBoardRepository;
import at.meks.agileboards.adapters.database.InMemoryTeamRepository;
import at.meks.agileboards.domain.core.usecases.board.AddBoard;
import at.meks.agileboards.domain.core.usecases.board.BoardRepository;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class BeanProducer {

    @Produces
    @ApplicationScoped
    public BoardRepository getBoardRepository() {
        return new InMemoryBoardRepository();
    }

    @Produces
    @ApplicationScoped
    public TeamRepository getTeamRepository() {
        return new InMemoryTeamRepository();
    }

    @Produces
    @ApplicationScoped
    public AddBoard getAddBoard(BoardRepository boardRepository, TeamRepository teamRepository) {
        return new AddBoard(boardRepository, teamRepository);
    }
}
