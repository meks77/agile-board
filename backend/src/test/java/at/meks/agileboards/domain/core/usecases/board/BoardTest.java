package at.meks.agileboards.domain.core.usecases.board;

import at.meks.agileboards.domain.core.model.board.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BoardTest {

    @Test
    void boardCreatedIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Board(null));
    }

}