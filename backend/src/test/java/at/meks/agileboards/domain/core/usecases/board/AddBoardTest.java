package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.Board;
import at.meks.agileboards.domain.core.model.board.BoardCreated;
import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddBoardTest {

    @InjectMocks
    private AddBoard usecase;

    @Mock
    private BoardRepository boardRepository;

    @SneakyThrows
    @Test
    void boardNameNotExisting() {
        BoardName boardName = new BoardName("My Board Name");
        TeamId teamId = new TeamId("My Team Id");

        Board board = usecase.add(teamId, boardName);

        assertNotNull(board);
        assertNotNull(board.id());
        assertEquals(teamId, board.teamId());
        assertEquals(boardName, board.name());
    }

    @SneakyThrows
    @Test
    void boardIsPersistedWhenAdded() {
        BoardName boardName = new BoardName("My Board Name");
        TeamId teamId = new TeamId("My Team Id");
        usecase.add(teamId, boardName);

        ArgumentCaptor<BoardCreated> boardCaptor = ArgumentCaptor.forClass(BoardCreated.class);
        verify(boardRepository).add(boardCaptor.capture());
        var boardCreated = boardCaptor.getValue();
        assertNotNull(boardCreated);
        assertEquals(teamId, boardCreated.teamId());
        assertEquals(boardName, boardCreated.name());
        assertNotNull(boardCreated.id());
    }

    @Test
    void boardIsNotPersistedIfNameAlreadyExists() {
        BoardName boardName = new BoardName("My Board Name");
        TeamId teamId = new TeamId("My Team Id");
        given(boardRepository.exists(teamId, boardName)).willReturn(true);

        assertThatExceptionOfType(BoardAlreadyExists.class)
                .isThrownBy(() -> usecase.add(teamId, boardName));
        verify(boardRepository, never()).add(any());
    }

}
