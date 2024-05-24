package at.meks.agileboards.domain.core.usecases.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BoardTest {

    @Test
    void boardCreatedIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Board(null));
    }

}