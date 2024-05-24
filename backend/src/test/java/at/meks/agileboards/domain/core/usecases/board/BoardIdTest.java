package at.meks.agileboards.domain.core.usecases.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BoardIdTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\r", "\t"})
    void idIsBlank(String uuid) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BoardId(uuid));
    }

    @Test
    void idIsToShort() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BoardId(""));
    }

}