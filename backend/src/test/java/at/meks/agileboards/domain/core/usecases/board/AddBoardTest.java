package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.Board;
import at.meks.agileboards.domain.core.model.board.BoardCreated;
import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;
import at.meks.agileboards.domain.core.usecases.team.TeamRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddBoardTest {

    @InjectMocks
    private AddBoard usecase;

    @Mock(strictness = Mock.Strictness.LENIENT)
    private BoardRepository boardRepository;

    @Mock(strictness = Mock.Strictness.LENIENT)
    private TeamRepository teamRepository;

    private final TeamId teamId = new TeamId("My Team Id");
    private final BoardName boardName = new BoardName("My Board Name");


    @BeforeEach
    void givenTeamExists() {
        given(teamRepository.exists(teamId)).willReturn(true);
    }

    @SneakyThrows
    @Test
    void boardNameNotExisting() {
        Board board = usecase.add(teamId, boardName);

        assertNotNull(board);
        assertNotNull(board.id());
        assertEquals(teamId, board.teamId());
        assertEquals(boardName, board.name());
    }

    @Test
    void teamIdNotExisting() {
        given(teamRepository.exists(teamId)).willReturn(false);

        assertThatIllegalStateException()
                .isThrownBy(() -> usecase.add(teamId, boardName))
                .withMessageContaining("teamId");
    }

    @SneakyThrows
    @Test
    void boardIsPersistedWhenAdded() {
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
        given(boardRepository.exists(teamId, boardName)).willReturn(true);

        assertThatExceptionOfType(BoardAlreadyExists.class)
                .isThrownBy(() -> usecase.add(teamId, boardName));
        verify(boardRepository, never()).add(any());
    }

    @Test
    void teamIdIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> usecase.add(null, boardName))
                .withMessageContaining("teamId");
    }

    @Test
    void boardNameIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> usecase.add(teamId, null))
                .withMessageContaining("boardName");
    }

}
