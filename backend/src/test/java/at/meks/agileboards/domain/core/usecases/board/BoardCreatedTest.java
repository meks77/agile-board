package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.BoardCreated;
import at.meks.agileboards.domain.core.model.board.BoardId;
import at.meks.agileboards.domain.core.model.board.BoardName;
import at.meks.agileboards.domain.core.model.team.TeamId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BoardCreatedTest {

    public static final TeamId TEAM_ID = TeamId.random();
    public static final BoardName BOARD_NAME = new BoardName("MyBoardName");
    public static final BoardId BOARD_ID = BoardId.random();

    @Test
    void idIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BoardCreated(null, TEAM_ID, BOARD_NAME))
                .withMessageContaining("id");
    }

    @Test
    void teamIdIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BoardCreated(BOARD_ID, null, BOARD_NAME))
                .withMessageContaining("teamId");
    }

    @Test
    void boardNameIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BoardCreated(BOARD_ID, TEAM_ID, null))
                .withMessageContaining("name");
    }

}